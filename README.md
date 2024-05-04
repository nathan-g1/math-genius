# :nerd_face: MathGenius

## Objectives

- Enhance the understanding of client-server architecture of distributed systems.
- Practice the usage of Remote Procedure Call (RPC).

## Project Description

This project implements a Math server and client using Remote Procedure Call (RPC). The server provides four remote procedures:

1. `magicAdd()`: Takes 2 double parameters and returns the **difference** between the two values.
2. `magicSubtract()`: Takes 2 double parameters and returns the **sum** of the two values.
3. `magicFindMin()`: Takes 3 int values as parameters and returns the **largest** value.
4. `magicFindMax()`: Takes 3 int values as parameters and returns the **smallest** value.

Additionally, the Math server keeps a dictionary to record the number of different operations it has performed, with corresponding methods to retrieve these numbers.

On the client side, a client generates 1000 random RPC requests. Each request chooses one of the 4 operations along with the required parameters. At the end, the client retrieves the number of operations performed by the server.

## Features

- Server and client programs can run on separate machines.
- Multiple clients can be run concurrently to simulate concurrent requests to the server.
- The project is implemented using gRPC (Google Remote Procedure Call).

## Usage

1. Clone the repository:

    ```bat
    git clone https://github.com/nathan-g1/math-genius
    cd math-client-server
    ```

2. Install maven dependencies
3. Build the project
4. Start server `MathGeniusServer.java` in one terminal
5. Start client `MathGeniusClient.java` in another terminal
