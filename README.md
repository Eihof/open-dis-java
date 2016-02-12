# Open DIS for Java

[![Build Status](https://travis-ci.org/open-dis/open-dis-java.svg?branch=master)](https://travis-ci.org/open-dis/open-dis-java)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/edu.nps.moves/open-dis/badge.svg)](https://maven-badges.herokuapp.com/maven-central/edu.nps.moves/open-dis)
[![Javadocs](http://www.javadoc.io/badge/edu.nps.moves/open-dis.svg)](http://www.javadoc.io/doc/edu.nps.moves/open-dis)

## Introduction

This repository contains Java code to implement the Distributed Interactive Simulation (DIS) IEEE-1278 standard, which is often used in military simulations.

The protocol code itself consists of classes that represent Protocol Data Units (PDUs).
These classes have fields, getters, and setters, and are able to marshal and unmarshal themselves to and from the DIS binary format.

The code was automatically generated via XMLPG, a tool that generates Java, CSharp, Objective-C, and C++ code automatically from an XML description.
The XML description files are contained in this directory; the canonical versions are saved in the "xml" directory. So is the XMLPG source code.

The code also has supporting classes that read and write PDUs from the network, log PDUs to a file, and more.

## XML Description File

An XML file that is an abstract description of the protocol classes are in the `DISXXXX.xml` files.
These files are used by XML Multi-Language Protocol Generator (XMLPG), which is in the http://github.com/open-dis/xmlpg repository.
The jar file for XMLPG is contained in the lib directory.

The XML file is processed by XMLPG and writes the Java protocol code.
The idea is to use the XMLPG tool to get the final product "close", and then manually modify the source code in subversion to tweak the last bit.
To this end there is a "patches" directory, which uses the Unix `patch` utility to modify the generated code via patch files.

## Java

The Java protocol classes are in the `src/main` directory, under the package `edu.nps.moves.dis`.
The code is generated by XMLPG's ant build file.
To generate the Java code from scratch, type `ant generateJavaDisSourceCode`.
This will generate the code in the above mentioned packages.

Supporting Java files are in the `edu.nps.moves.*` directories.
These include classes that unmarshall IEEE 1278.1 files.

= Using the Java Library

Include the `open-dis-<version-number>.jar` file in your project, along with the supporting jar files in the `lib` directory.
(The `xmlpg.jar` file is not needed.)

## Release Notes

### The 3.0 release highlights:

* CSharp implementation from Peter Smith. He wrote a new xmlpg class to generate CSharp code.

* Automated patch application. The Unix patch utility is used to modify the auotmatically generated code. See the patch directory in the open-dis directory. Patches are applied via the ant task "patch". Windows users; need to install cygwin utilities to be able to use patch, and make some minor changes to the ant build.xml file.

* Robert Harder has reworked the Java marshal and unmarshal code to use much more memory efficient NIO classes, which dramatically reduce the amount of temporary object memory generated.

* Sheldon Snyder has contributed dead reckoning algorithms in Java, available in the `edu.nps.moves.deadreckoning` package.

* Tariq Rashid has contributed an xplane gateway, which reads xplane flight simulator UDP packets and converts them to DIS. He's also implemented a KML gateway, which allows low-resolution updates to markers on Google Earth. This code is a bit rough right now--it needs to be cleaned up. There's no guarantee it works. This is available in the xplane directory of open-dis.

* xmlpg, which generates the source code, is now included in the open-dis lib directory. This allows code to be generated completly withing the open-dis directory, rather than trying to do cross-directory builds. See the `generateDisSourceCode` ant target.

* Enumerations. The SISO EBV XML document was used to generate Java enumeration classes. This can be extended as the EBV XML document is completed. A C++ enumerations project would be a useful project for some developer. The enumerations are used in several places in the open-dis code, notably the PduFactory.

* Unit tests. The Java code has added some JUnit 4.4 unit tests, primarily to test that post-processing source code patches have been applied correctly. These can be extended to provide more complete test coverage.

### 4.0 release highlights

* SQL support.
* Modified & rationalized repository layout.
* Added mobile support in addition to desktop support.

## License

All code is BSD license. See `License.txt`.

## Software release process

Once enough changes have been made we cut a new release and deploy it to Maven Central.

In a nutshell the person performing the release will need:
 * A Sonatype JIRA account
 * Your JIRA credentials placed in your `~/.m2/settings.xml`
 * Your GPG key published

For more info view this [guide](https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide).

Once that's done, for each release do the following commands:

    $ mvn release:clean
    $ mvn release:prepare
    $ mvn release:perform
