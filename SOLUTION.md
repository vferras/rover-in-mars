# MY SOLUTION

## How to run the solution
```bash
./run.sh
```
This script will build a docker images from the application, and run it.

When building the docker image, the app is compiled and the tests are executed.

## Comments of the solution

- I've used a functional approach as is my preferred one. I assume the little
memory overhead (every top level function is compiled into a class).

- As I'm lacking more time to invest, I want to comment that all the pure code is tested
but function with IO is not tested. In a production environmenet I would use integration and
end to end testing when there's IO.

- I've separated the functions into two files:
    - `MarsRover.kt` contains all the core and user related interactions
    - `MarsRoverController.kt` contains all the movement related actions

- Sometimes I've used different ways of solving similar problems, like when I use
recursive functions and foreachs to achieve recursivity.