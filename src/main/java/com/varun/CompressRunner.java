package com.varun;

import com.varun.tree.HuffTree;
import com.varun.tree.TreeBuilder;
import picocli.CommandLine;
import picocli.CommandLine.ParameterException;

import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Map;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

@Command(
        name = "jcompress",
        mixinStandardHelpOptions = true,
        version = " jcompress 1.0",
        description = "A file compression utility using Huffman encoding"
)
public class CompressRunner implements Callable<Integer> {

    @Option(names = {"-e", "--encode"}, description = "Encode mode")
    private boolean encode;

    @Option(names = {"-d", "--decode"}, description = "Decode mode")
    private boolean decode;

    @Option(names = {"-i", "--input-file"}, description = "Input file path")
    private String inputFile;

    @Option(names = {"-o", "--output-file"}, description = "Output file path")
    private String outputFile;

    @Option(names = {"-h", "--header-file"}, description = "Header file path (for decode mode)")
    private String headerFile;

    public static void main(String[] args) {
        System.exit(new CommandLine(new CompressRunner()).execute(args));
    }

    @Override
    public Integer call() throws Exception {
        validate();
        if (encode) {
            performEncoding();
        } else {
            performDecoding();
        }
        return 0;
    }

    private void performDecoding() throws FileNotFoundException, FileAlreadyExistsException {
        // Decode
        String encodedData = FileUtil.readContent(inputFile);
        Map<Character, Long> characterLongHashMap = FileUtil.deserializeHashMap(headerFile);
        HuffTree encodedRoot = TreeBuilder.buildTree(characterLongHashMap);
        String decodedData = Translator.decode(encodedData, encodedRoot);

        // Write to output file
        FileUtil.writeContent(decodedData, outputFile);
    }

    private void performEncoding() throws FileNotFoundException, FileAlreadyExistsException {
        // Encode
        String data = FileUtil.readContent(inputFile);
        Map<Character, Long> frequencyMap = FrequencyCalculator.calculateFrequency(data);
        HuffTree root = TreeBuilder.buildTree(frequencyMap);
        String encoded = Translator.encode(data, root);

        // Write to output
        FileUtil.writeContent(encoded, outputFile);
        FileUtil.serializeHashMap(frequencyMap, headerFile);
    }

    private void validate() {
        if (encode && decode) {
            throw new ParameterException(new CommandLine(this), "Specify either --encode or --decode, not both.");
        }
        if (!(encode || decode)) {
            throw new ParameterException(new CommandLine(this), "Specify either --encode or --decode.");
        }
        if (inputFile == null || outputFile == null || headerFile == null) {
            throw new ParameterException(new CommandLine(this), "Specify --input-file, --header-file & --output-file.");
        }
    }
}
