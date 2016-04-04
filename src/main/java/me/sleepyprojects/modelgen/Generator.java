/* 
 * Copyright (c) 2016 Filip Hornak
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */
package me.sleepyprojects.modelgen;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Generator class creates code from abstract code blocks.
 */
public class Generator {

    private final Deque<Block> blockStack;
    private final Deque<Part> partStack;
    private final LinkedList<String> partNames;
    private final Deque<String> blockName;
    private final Language lang;
    private OutputStream os;
    private Block block;
    private Writer streamWriter;
    private boolean flattened;

    public Generator(Language lang) {
        this.blockStack = new LinkedList<>();
        this.partStack = new LinkedList<>();
        this.blockName = new LinkedList<>();
        this.partNames = new LinkedList<>();
        this.lang = lang;
    }

    /**
     * Release all resources, except of output stream.
     */
    public void clear() {
        this.flattened = false;
        this.block = null;
        this.streamWriter = null;
        this.blockName.clear();
        this.blockStack.clear();
        this.partNames.clear();
        this.partStack.clear();
    }

    /**
     * Runs flattening process that converts Block to String.
     */
    public void flatten() {
        flattened = true;
        blockStack.push(this.block);
        while (!blockStack.isEmpty()) {
            if (!blockStack.peek().hasBlocks() || isPrepared(blockStack.peek())) {
                flattenTop();
            }
            else {
                for (Map.Entry<String, Block> blockEntry : blockStack.peek().getBlockIterator()) {
                    blockName.push(blockEntry.getKey());
                    blockStack.push(blockEntry.getValue());
                }
            }
        }
    }

    /**
     * Writes flattened Block to stream
     *
     * @throws IOException
     */
    public void flush() throws IOException {
        if (!isFlattened()) {
            flatten();
        }
        getStreamWriter().write(partStack.peek().toString());
        getStreamWriter().flush();
    }

    public String getCode() {
        if (isFlattened()) {
            return partStack.peek().toString();
        }
        throw new RuntimeException("Not ready");
    }

    /**
     * @return OutputStreamWriter object.
     */
    public Writer getStreamWriter() {
        if (streamWriter == null) {
            if (os == null) {
                throw new RuntimeException("Can not create writer when output stream is not specified");
            }
            streamWriter = new OutputStreamWriter(this.os);
        }
        return streamWriter;
    }

    /**
     * Returns if flattening process was run.
     *
     * @return true when flattening process was performed successfully.
     */
    public boolean isFlattened() {
        return flattened && blockStack.isEmpty();
    }

    /**
     * Sets Block that will be flattened.
     *
     * @param block code block
     */
    public void setBlock(Block block) {
        this.flattened = false;
        this.block = block;
    }

    /**
     * Sets output stream
     *
     * @param os output stream object
     */
    public void setOutputStream(OutputStream os) {
        if (os == null) {
            return;
        }
        this.os = os;
    }

    private void flattenTop() {
        final Block block = blockStack.poll();
        Context context = new VelocityContext(lang.getPredefined());
        Map<String, List<Part>> iterableBlockMap = new HashMap<>();
        for (int i = 0; i < block.getBlockCount(); i++) {
            final String name = partNames.poll();
            if (name.startsWith("L:")) {
                final int lastIdx = name.lastIndexOf('#');
                final String currentListName = name.substring(2, lastIdx);
                final int arrayIdx = Integer.parseInt(name.substring(lastIdx + 1));
                if (!iterableBlockMap.containsKey(currentListName)) {
                    iterableBlockMap.put(currentListName, new IndexedList<>());
                }
                iterableBlockMap.get(currentListName).add(arrayIdx, partStack.poll());
                continue;
            }
            context.put(name, partStack.poll());
        }
        if (!iterableBlockMap.isEmpty()) {
            for (Map.Entry<String, List<Part>> partEntry : iterableBlockMap.entrySet()) {
                context.put(partEntry.getKey(), new PartsIterable<>(partEntry.getValue()));
            }
        }
        for (Map.Entry<String, ?> partsEntry : block.getPartIterator()) {
            context.put(partsEntry.getKey(), partsEntry.getValue());
        }
        Template tpl = lang.getManager().loadTemplate(block);
        final StringWriter codeBuffer = new StringWriter();
        try {
            tpl.merge(context, codeBuffer);
        }
        catch (IOException e) {
            // TODO add logger
            e.printStackTrace();
        }
        final String code = codeBuffer.toString();
        final FlatPart flatCode = new FlatPart(code);
        partStack.push(flatCode);
        if (!blockName.isEmpty()) {
            partNames.push(blockName.poll());
        }
    }

    private boolean isPrepared(Block peek) {
        if (!peek.hasBlocks()) {
            return true;
        }
        final List<String> subParts;
        if (peek.getBlockCount() == partNames.size()) {
            subParts = partNames;
        }
        else {
            subParts = partNames.subList(0, peek.getBlockCount() - 1);
        }
        return subParts.containsAll(peek.getBlockNames());
    }
}
