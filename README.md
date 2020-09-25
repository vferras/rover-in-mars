# Wallapop Backend Tech Test

Hello candidate!!

Welcome to Wallapop and its backend technical test

## Mars Rover refactoring kata

We need to improve the existing solution that translates commands sent from Earth to instructions that are understood by our Rover on Mars

Currently, the code is very complicated and tangled, so we'd like you to invest some time to clean it up

### Functional requirements
```
Given:
 - a two dimensional map of Mars
 - the initial starting point and direction of the Rover
 
When:
 - a command is received
   move `forward` or `backward` or rotate `left` or `right` (90 degrees)

Then:
 - move the Rover
   if the Rover disappears over the edge of the map (the horizon), continue on the other side (remember, Mars is a sphere)
```

#### Bonus point

After ensuring that the functional requirements have been met, as a bonus point (not necessary but more than welcome), add a new feature:
```
Given:
 - a list of obstacles with their exact location
 
When:
 - Rover moves

And:
 - Rover encounters an obstacle

Then:
 - report back the obstacle. The Rover should stay in its previous position
```

## Your solution

- Change and evolve the provided code as much as you want, but **do not create a new project from scratch**
- Use any JVM language, but if you want to use one other than Java please convert the provided code in the first commit
- Ensure your solution is self-executable and self-compiled
- Feel free to use any pattern, framework or whatever you want
- Bug free will be a plus
- Fill in SOLUTION.md and explain the important features of your solution 

## Our evaluation

- We will focus on your design and on your own code over the usage of frameworks and libraries
- We will also take into account the evolution of your solution, not just the delivered code
- We will evolve your solution with feasible features and evaluate how complex it is to implement them

## How to do it

This project is a [Template Project](https://help.github.com/en/articles/creating-a-repository-from-a-template) that allows you to create a new project of your own based on this one

We would like you to maintain this new repository as private, and give access to `wallabackend` to evaluate it once you are done with your solution

Please, let us know as soon as you finish, otherwise we will not start the review

Thanks & good luck!!
