package day07

import java.io.File as JavaFile

class DirectoryParser(filePath: String) {
    private val fileSystem: Directory

    init {
        fileSystem = parseCommandsAndOutput(filePath)
    }

    fun getSize(maxSize: Int): Int {
        val total = addTotalSizesForMax(fileSystem, maxSize)
        println("Total files for directories with max size of $maxSize is $total")
        return total
    }

    fun getDirectoryToFreeUpSpace(): Int {
        val maxStorage = 70000000
        val neededSpace = 30000000
        val totalUsedSpace = parseSize(fileSystem)
        val unusedSpace = maxStorage - totalUsedSpace
        val directorySizeToDelete = neededSpace - unusedSpace
        val dirToDelete = findDirectoryNearestToValue(fileSystem, directorySizeToDelete, parseSize(fileSystem))
        println("Directory that should be deleted has size of $dirToDelete")
        return dirToDelete
    }

    private fun findDirectoryNearestToValue(dir: Directory, size: Int, closestValue: Int): Int {
        var value = closestValue
        val valueToCheck = parseSize(dir)
        if (valueToCheck in size until closestValue) {
            value = valueToCheck
        }
        dir.directories.forEach {
            val update = findDirectoryNearestToValue(it, size, value)
            if (update < value) {
                value = update
            }
        }
        return value
    }

    private fun parseSize(directory: Directory): Int {
        var size = directory.files.sumOf { it.size }
        directory.directories.forEach {
            size += parseSize(it)
        }
        return size
    }

    private fun addTotalSizesForMax(dir: Directory, maxSize: Int): Int {
        var total = 0
        val parse = parseSize(dir)
        if (parse in 1..maxSize) {
            total += parse
        }
        dir.directories.forEach {
            total += addTotalSizesForMax(it, maxSize)
        }
        return total
    }

    private fun parseCommandsAndOutput(filePath: String): Directory {
        var directory: Directory? = null
        val stream = JavaFile(filePath).inputStream()
        var currentPath = ""
        stream.bufferedReader().forEachLine {
            if (it.startsWith("$")) {
                if (it.contains("$ cd")) {
                    if (it.contains("..")) {
                        currentPath = directory!!.getChildDirectoryByPath(currentPath)?.parent?.path ?: ""
                    } else {
                        val path = currentPath + it.replace("$ cd ", "")
                        directory = directory ?: Directory(path, null)
                        currentPath = path
                    }
                }
            } else {
                if (it.startsWith("dir")) {
                    val path = currentPath + it.replace("dir ", "")
                    val basePath = directory?.path ?: ""
                    if (currentPath == basePath) {
                        val newDir = Directory(path, directory)
                        directory!!.directories.add(newDir)
                    } else {
                        val newDir = Directory(path, directory!!.getChildDirectoryByPath(currentPath))
                        directory!!.getChildDirectoryByPath(currentPath)?.directories?.add(newDir)
                    }
                } else {
                    val fileSplit = it.split(" ")
                    if (currentPath == directory?.path) {
                        directory!!.files.add(SystemFile(fileSplit.last(), fileSplit.first().toInt()))
                    } else {
                        directory!!.getChildDirectoryByPath(currentPath)?.files?.add(SystemFile(fileSplit.last(), fileSplit.first().toInt()))
                    }
                }
            }
        }
        return directory!!
    }
}