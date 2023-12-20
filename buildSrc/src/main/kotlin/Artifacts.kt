import org.gradle.api.Project


fun Project.group(): String = property("GROUP") as String
fun Project.versionName(): String = property("VERSION_NAME") as String

fun Project.sampleVersionCode() = 1
fun Project.sampleVersionName() = "0.0.1"
