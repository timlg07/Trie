# Trie
An implementation of the trie data structure in Java with an inbuilt shell to execute commands.
  
## Documentation
The Javadoc is available at [tim-greller.de/data/Trie/JavaDoc](https://tim-greller.de/data/Trie/JavaDoc/).

## Data Structure
The basic form is that of a linked set of nodes, where each node contains an array of child pointers, one for each symbol in the alphabet.
(Read more on [Wikipedia](https://en.wikipedia.org/wiki/Trie#Implementation_strategies))  
Here is a example of a trie; Each node is represented by its character, each value is displayed in \[brackets\] and pointers to child nodes are shown as lines.  
![Trie: Object diagram](structure%20example.png)  

This trie can be created in the shell executing the following commands:
```
add andrea 5
add andreas 8
add anna 2
add hanna 7
add hannes 3
add hans 1
add maria 1
add marie 2
add mark 2
add markus 3
add max 13
```
And its string representation is:
`+(a(n(d(r(e(a[5](s[8]))))n(a[2])))h(a(n(n(a[7]e(s[3]))s[1])))m(a(r(i(a[1]e[2])k[2](u(s[3])))x[13])))`

## Shell
The shell holds and manages a trie on which you can perform a set of different operations. The `help` command lists all of the available commands with their syntax:
```
Trie enables you to store integers in a tree data structure using strings as keys.

Available commands:
add <key> <value>     Inserts the value for a new key into the trie. Fails if the key already has a value assigned.
change <key> <value>  Changes the value for the given key. Fails if no value is associated with the key.
delete <key>          Removes a data element. Fails if no value is associated with the key.
points <key>          Prints the value of the specified key. Fails if no value is associated with the key.
trie   Prints the structure of the current trie.
help   Shows this help text.
new    Creates a new trie and discards the old data structure.
quit   Exits the program.

Note that the key must contain lowercase letters only.
```

A dialogue with the shell could look like this: [Tests.txt](Tests.txt)
