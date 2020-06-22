plugins {
    java
    war
}

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
//    providedCompile("javax.servlet:javax.servlet-api:3.1.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.6.2")
    implementation("org.postgresql:postgresql:42.1.4")
    implementation("org.projectlombok:lombok:1.18.12")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
}