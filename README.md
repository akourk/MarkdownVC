---
Title: Test Markdown
StoryID: 3e1469e4-180e-4635-bb8e-0a938a0c10da
StoryVersion: 72
Filename: testmarkdown.md
Description: this looks like a hidden section that can be used for metadata tags or whatever
---

# MarkdownVC and Markdown

# Contents

- [MarkdownVC](#markdownvc)
    - [Linting](#linting)
        - [Metadata](#metadata)
        - [Title](#title)
- [Markdown](#markdown)
    - [Example](#example)
    - [Headings](#headings)
    - [Links](#links)
    - [Formatting](#formatting)
        - [Code Blocks](#code-blocks)
        - [Quote Blocks](#quote-blocks)
    - [Bullets](#bullets)
    - [Highlighting](#highlighting)
    - [Citation](#citation)
    - [Acknowledgements](#acknowledgements)

## MarkdownVC

MarkdownVC stuff is here:

### Linting

#### Metadata

All files should begin with metadata in this format:

```markdown
---
Title: Title Of Story
StoryID: UUID
StoryVersion: 0
Filename: titleofstory.md
Description: one-line description of story
---
```

Metadata should be followed by an empty line.

#### Title

title should immediately follow the empty line following the metadata.

## Markdown

Any markdown-specific stuff will be down below:

### Example
See: [this is a link to a .md file on my github](https://raw.githubusercontent.com/akourk/MarkdownVC/main/README.md)

### Headings

# heading level 1

## heading level 2

### heading level 3

#### heading level 4

##### heading level 5

###### heading level 6

## Big title thing #2 again

### Links

very cool. lets try linking to another .md file. [clickhere](./editablemarkdownfile.md) nice work.

lets do some variations of text: _italic_, **bold**, `monospace`.

Horizontal rule:
---

cool.

### Formatting

#### Code Blocks

lets add some code:

```java
class Main {
    public static void main(String[] args) {
        System.out.println("markdown seems great");
    }
}
```

#### Quote Blocks

here's a quote block:

> Somewhere, something incredible is waiting to be known

here's a nested quote block:

> _Crab:_
> > What a counterintuitive picture you paint, Dr. Anteater. It seems that, if what you say is true, in order to grasp the whole structure, you have to describe it omitting any mention of its fundamental building blocks.
> 
> _Anteater:_
> > Perhaps I can make it a little clearer by an analogy. Imagine you have before you a Charles Dickens novel.
> 
> _Achilles:_
> > The Pickwick Papers--will that do?
> 
> _Anteater:_
> 
> > Excellently! And now imagine trying the following game: you must find a way of mapping letters onto ideas, so that the entire Pickwick Papers makes sense when you read it letter by letter.

And these can be deeply nested.

## Lets do some other sections

### Here's Big Title Thing #3

this looks great

### Bullets

 lets see if we can do some bullet points or something.

- bullet point
- another one

lets try numbered bullets:

1. numbered bullet
2. numbered bullet two

nested bullets:

1. fruits
    * apple
    * banana
2. vegetables
    - carrot
    - broccolli

### Highlighting

lets try making some highlights. here's one `something`.
here's another one. this is a link and a highlight: [`./editablemarkdownfile.md`]

can we link to a section?

### Citation

can we site something? 
[markdown2](./editablemarkdownfile.md)<sup>[1]</sup>

lets make that [1] clickable:
 file[<sup>[1]</sup>](./editablemarkdownfile.md)


### Acknowledgements

[Aaron Swartz](https://en.wikipedia.org/wiki/Aaron_Swartz), honestly.