package day07

class Directory(
    val path: String,
    val parent: Directory?,
    var directories: MutableList<Directory> = mutableListOf(),
    val files: MutableList<SystemFile> = mutableListOf()
) {
    fun getChildDirectoryByPath(path: String): Directory? {
        var dir: Directory? = null
        directories.forEach {
            if (it.path == path) {
                dir = it
            } else if (it.directories.size > 0) {
                dir = it.getChildDirectoryByPath(path)
            }
        }
        return dir
    }
}
