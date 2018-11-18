package main.util

fun meassureElapsedTime(function: () -> Unit): Long {
    val startTime = System.currentTimeMillis()
    function()
    return System.currentTimeMillis() - startTime
}