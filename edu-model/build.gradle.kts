import groovy.lang.GroovyObject


val modelVersion: String by project

version = modelVersion

java {
    withSourcesJar()
}

dependencies {

}
