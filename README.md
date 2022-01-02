## Automated Writing

An automatic text generator created for the NUS CS2040S Algorithms and Data Structures module. Using the Markov Model, this automatic writing program can easily produce pages and pages of new text which will adapt to the chosen style. If you use an old essay as input, your new essay will sound just like it was written by you! If you use Shakespeare as input, your new essay will sound as if it was written by the Bard himself.

The basic idea is to take an input text and calculate its statistical properties. For example, given a specific string “prope”, what is the probability that the next letter is an ‘r’? What is the probability that the next letter is a ‘q’? Your program will take a text as input, calculate this statistical information, and then use it to produce a reasonable output text. Claude Shannon first suggested this technique in a seminal paper A Mathematical Theory of Communication (1948). This paper contained many revolutionary ideas, but one of them was to use a Markov Chain to model the statistical properties of a particular text. Markov Chains are now used everywhere; for example, the Google PageRank algorithm is built on ideas related to Markov Chains.

## How it works
The text generator will take in sample text at first and form a Markov Model based on the next letter/word that comes after it. Using the Markov Model, it will then generate the text of certain length as specified by the user.

## Running the text generator
Run ModifiedTextGenerator.java to generate text. The text generator class takes three input parameters, i.e., the main method has
argument (k, n, filename):
1. k, the order of the Markov model;
2. n, the number of characters to generate;
3. the filename of the text to use as a model.

Compile in terminal and run the file with 3 input. For eg,
```
javac *.java
java TextGenerator 100 1000 ./sampleText/Alice.txt
```
Do note that the value of k represents the number of characters that appeared previously which the Markov Model looks at. As such, a small value of k, (i.e. 3) would result in incoherent text but larger values of k returns text that is coherent and flows smoothly.
