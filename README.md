# ![JMMC logo](https://github.com/JMMC-OpenDev/.github/blob/main/doc/JMMC-logo.jpg) OpenDev - ASPRO2-conf
This module contains the ASPRO2 configuration files developed by the JMMC technical team.


## License
See [![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)


## Links:
- [ASPRO2](https://www.jmmc.fr/aspro)
- [ASPRO2 @ github](https://github.com/JMMC-OpenDev/aspro)
- [JMMC OpenDev @ github](https://github.com/JMMC-OpenDev/)
- [JMMC releases](https://www.jmmc.fr/releases/)
- [CI nightly builds](https://github.com/JMMC-OpenDev/jmmc-java-build/actions/workflows/build.yml)
- [JMMC Web site](https://www.jmmc.fr)


## Documentation
This module contains several XML files describing optical interferometers & their instruments, mainly VLTI & CHARA.

See [ASPRO2 configuration description](https://jmmc-opendev.github.io/aspro-doc/aspro-doc.html#supported-interferometers-and-instruments)

These XML files are based on few XML schemas (xsd) to validate their syntax and internal references:
- [`AsproOIModel.xsd`](https://github.com/JMMC-OpenDev/aspro-conf/tree/master/src/main/resources/fr/jmmc/aspro/model/AsproOIModel.xsd): the ASPRO2 data model for the interferometer description and observation settings (asprox)
- `AsproRawObsModel.xsd`: the ASPRO2 data model for raw observations (obsportal)
- `targetModel.xsd`: the data model for geometric / analytical models (shared between ASPRO2 and LITpro)

These xml schemas are documented (thanks to xsddoc) with HTML pages at: [ASPRO2 data model](https://apps.jmmc.fr/~swmgr/xsddoc/aspro-oi/0.1/html).

The XLST script `AsproOIConfigurations.xsl` let you generate HTML pages to better read the configuration file: see the [Latest Aspro Configuration](https://apps.jmmc.fr/~swmgr/AsproOIConfigurations/)


All configuration files are in the [`src/main/resources/fr/jmmc/aspro/model`](https://github.com/JMMC-OpenDev/aspro-conf/tree/master/src/main/resources/fr/jmmc/aspro/model) folder:
- `AsproOIConfigurations.xml`: ASPRO2's inventory to list enabled configuration files (+ checksum to trust their content)
- optical interferometers
  - [`VLTI.xml`](https://github.com/JMMC-OpenDev/aspro-conf/tree/master/src/main/resources/fr/jmmc/aspro/model/VLTI.xml): ESO VLTI interferometer [Very Large Telescope Interferometer](https://www.eso.org/sci/facilities/paranal/telescopes/vlti.html)
  - [`CHARA.xml`](https://github.com/JMMC-OpenDev/aspro-conf/tree/master/src/main/resources/fr/jmmc/aspro/model/CHARA.xml): CHARA interferometer [Center for High Angular Resolution Astronomy](https://www.chara.gsu.edu/)
  - `NPOI.xml`: NPOI interferometer
  - `SUSI.xml`: SUSI interferometer
  - `DEMO.xml`: DEMO interferometer (VLTI schools)
  - `MROI.xml`: (disabled) MROI (future)
- extra (single-dish): OHP.xml, PARANAL.xml, SUTHERLAND.xml

Finally aspro-conf contains 1 atmosphere transmission profile (ESO VLTI seasonal average at high resolution = 60,000) in the FITS file `skytable_mean_atm.fits (3.7Mb)`.


## Build
This JMMC module uses java / maven to build the jar file, to be integrated into ASPRO2 software.

Requirements:
- OpenJDK 8+
- Maven 3.6+

See [JMMC Java Build](https://github.com/JMMC-OpenDev/jmmc-java-build)
See [CI nightly builds](https://github.com/JMMC-OpenDev/jmmc-java-build/actions/workflows/build.yml)
