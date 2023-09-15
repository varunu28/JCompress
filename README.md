# JCompress
A text compression command line tool using Huffman tree

## How to build?
```agsl
./gradlew shadowJar
alias jcompress='java -jar build/libs/jcompress-shadow.jar'
```

## How to run?
```agsl
// Encoding
jcompress --encode -i data.txt -o encoded_data.txt -h header.txt

// Decoding
jcompress --decode -i encoded_data.txt -o decoded_output.txt -h header.txt

// Verify
diff decoded_output.txt data.txt // Empty result
```
