# :crossed_swords: KnightBoard

This test could be solved using plain Java to achieve a smaller artifact, with much fewer dependencies and faster execution times.

Having no guidelines I’ve chosen to implement a solution that better showed how I usually develop Java back-end architectures using the Spring framework.

The choice of using a reactive stack (WebFlux) with blocking operations, while not best suited for the two GET operations on APIs, was just to try the new Spring features.

Clearly this is over-kill given the simple problem, but I hope you will appreciate the effort.

**4th July**: Given the short timeframe of 3 days across the weekend, unfortunately, I wasn’t able to develop in TDD or to complete the test implementations. Maybe I’ll implement them as an exercise in the next few days.

# July 9th Update

During the free time at the end of the week I was able to implements a few things:
- code refactoring: introduction of an abstract class "Command"
- fixes on board and commands validation
- **TESTS implementation**:
  - integration
  - functional
- **BUG fixing**:
  - return OUT_OF_THE_BOARD if the knight goes out SOUTH or WEST (negative coordinates)
  - prevent multiple prints (stop the program if and exception is thrown during initialization)

# Run the project
Execute the following commands in the main project folder
```
docker build -t knight_board:latest .

docker run \
-e BOARD_API=https://storage.googleapis.com/jobrapido-backend-test/board.json \
-e COMMANDS_API=https://storage.googleapis.com/jobrapido-backend-test/commands.json \
knight_board:latest

```
