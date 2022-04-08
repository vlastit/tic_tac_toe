package tictactoe

import kotlin.math.abs

const val THREE: Int = 3

/*
 * Tic-Tac-Toe game
 */
fun main() {
    startGame()
}

fun startGame() {
    val grid: MutableList<MutableList<Char>> = mutableListOf(
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
        mutableListOf(' ', ' ', ' '),
    ) // Tic-Tac-Toe grid

    print("\n")
    displayGrid(grid)

    var currentPlayer = 'X'

    var gameEnded: Boolean = false

    // prompt a user to make a move
    do {
        print("Enter the coordinates:")

        val playerMove: MutableList<Int?> = mutableListOf()

        readln().split(' ').map { playerMove.add(it.toIntOrNull()) }

        val playerMoveRowErrorString: String? = checkPlayerInput(playerMove[0])
        val playerMoveColumnErrorString: String? = checkPlayerInput(playerMove[1])

        if (playerMoveRowErrorString != null) {
            println(playerMoveRowErrorString)
            continue
        } else if (playerMoveColumnErrorString != null) {
            println(playerMoveColumnErrorString)
            continue
        } else if (grid[playerMove[0]!! - 1][playerMove[1]!! - 1] != ' ') {
            println("This cell is occupied! Choose another one!")
            continue
        }

        grid[playerMove[0]!! - 1][playerMove[1]!! - 1] = currentPlayer

        if (currentPlayer == 'X') currentPlayer = 'O' else currentPlayer = 'X'

        displayGrid(grid)

        // analise grid
        gameEnded = analiseGrid(grid)
    } while (!gameEnded)
}

/*
 * Display Tic-Tac-Toe grid
 */
fun displayGrid(grid: MutableList<MutableList<Char>>) {
    println("---------")
    println("| ${grid[0].joinToString(" ")} |")
    println("| ${grid[1].joinToString(" ")} |")
    println("| ${grid[2].joinToString(" ")} |")
    println("---------")
}

/*
 * Check that player input correct data
 */
fun checkPlayerInput(move: Int?): String? {
    return when (move) {
        null -> "You should enter numbers!"
        !in 1..THREE -> "Coordinates should be from 1 to $THREE!"
        else -> null
    }
}

/*
 * analise grid
 */
fun analiseGrid(grid: MutableList<MutableList<Char>>): Boolean {
    var result: Char? = null

    result = checkHorizontalWin(grid)

    if (result != null) {
        println("$result wins")
        return true
    }

    result = checkVerticalWin(grid)

    if (result != null) {
        println("$result wins")
        return true
    }

    result = checkDiagonalWin(grid)

    if (result != null) {
        println("$result wins")
        return true
    }

    if (checkDraw(grid)) {
        println("Draw")
        return true
    }

    return false
}

/*
 * check horizontal win
 */
fun checkHorizontalWin(grid: MutableList<MutableList<Char>>): Char? {
    for (list in grid) {
        if (list[0] == list[1] && list[1] == list[2] && list[0] == 'X') return 'X'
        if (list[0] == list[1] && list[1] == list[2] && list[0] == 'O') return 'O'
    }

    return null
}

/*
 * check vertical win
 */
fun checkVerticalWin(grid: MutableList<MutableList<Char>>): Char? {
    for (i in 0..grid.lastIndex) {
        if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] == 'X') return 'X'
        if (grid[0][i] == grid[1][i] && grid[0][i] == grid[2][i] && grid[0][i] == 'O') return 'O'
    }

    return null
}

/*
 * check diagonal win
 */
fun checkDiagonalWin(grid: MutableList<MutableList<Char>>): Char? {
    if (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] == 'X') return 'X'
    if (grid[0][0] == grid[1][1] && grid[0][0] == grid[2][2] && grid[0][0] == 'O') return 'O'
    if (grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2] && grid[2][0] == 'X') return 'X'
    if (grid[2][0] == grid[1][1] && grid[2][0] == grid[0][2] && grid[2][0] == 'O') return 'O'

    return null
}

/*
 * check empty cells exist in grid
 */
fun checkDraw(grid: MutableList<MutableList<Char>>): Boolean {
    for (list in grid) {
        for (element in list) {
            if (element == ' ') return false
        }
    }

    return true
}
