# Trie
An implementation of the trie data structure in Java with an inbuilt shell to execute commands.
  
## Documentation
The Javadoc is available at https://tim-greller.de/data/Trie/JavaDoc/.

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
