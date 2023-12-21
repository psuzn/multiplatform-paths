import org.gradle.api.Project


fun Project.group() = property("GROUP") as String
fun Project.versionName() = property("VERSION_NAME") as String

fun Project.sampleVersionCode() = 1
fun Project.sampleVersionName() = "0.0.1"
