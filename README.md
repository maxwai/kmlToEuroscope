![test](https://badgen.net/badge/language/Java/green)
[![GitHub license](https://badgen.net/github/license/maxwai/kmlToEuroscope)](LICENSE)

# kmlToEuroscope
A Tool to convert kml files from google earth to Files for Euroscope

## Getting Started

### Prerequisites

You will need Java Version 14 or later to make it work.
It may work with lower Java versions, but it was programmed using the Java 14 JDK. <br>
This program will only work on Windows.

### Installing

Download the [kmlToEuroscope.jar](kmlToEuroscope.jar) file and save it in the folder with the kml file for ease of use.

### How to Use

* Double click the jar file. A Windows terminal window should show up.
* A list will show up with all `.kml` files will show up.
  * If the wanted `.kml` file is in a different location, copy paste the path from Windows Explorer
    into the program once asked.
* Choose the wanted File, after a few second the program will say that it is finished.
  At that point you can close the Terminal.
  
## TODO

- [X] Make `.kml` to `.ese` interpreter
- [ ] Make `.kml` to `.sct` interpreter
- [ ] Add a way to group FreeText and GEO together
    - Idea: add such groups in separate Google Earth folders with the group name as the folder name

## License [![GitHub license](https://badgen.net/github/license/maxwai/kmlToEuroscope)](LICENSE)

This project is licensed under the GNU General Public License - see the [LICENSE](LICENSE) file for details