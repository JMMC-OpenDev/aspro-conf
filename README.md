# Aspro2-conf    ![JMMC logo](doc/JMMC-logo.jpg)

This module contains the ASPRO2 configuration files developed by the JMMC technical team.

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](LICENSE)


See [ASPRO2](https://jmmc.fr/aspro)
See [ASPRO2 @ github](https://github.com/JMMC-OpenDev/aspro)
See [JMMC OpenDev @ github](https://jmmc.fr/JMMC-OpenDev)


## Documentation

This module contains several XML files describing optical interferometers & their instruments, mainly VLTI & CHARA.

See [ASPRO2 configuration description](http://www.jmmc.fr/twiki/bin/view/Jmmc/Software/JmmcAspro2#Supported_interferometers_and_in)

These XML files are based on few XML schemas (xsd) to validate their syntax and internal references:
- `AsproOIModel.xsd`: the ASPRO2 data model for the interferometer description and observation settings (asprox)
- `AsproRawObsModel.xsd`: the ASPRO2 data model for raw observations (obsportal)
- `targetModel.xsd`: the data model for geometric / analytical models (shared between ASPRO2 and LITpro)

These xml schemas are documented (thanks to xsddoc) at: [ASPRO2 data model](http://apps.jmmc.fr/~swmgr/xsddoc/aspro-oi/0.1/html).

The XLST script `AsproOIConfigurations.xsl` let you generate HTML pages to better read the configuration file. See the [Latest Aspro Configuration](http://apps.jmmc.fr/~swmgr/AsproOIConfigurations/)


All configuration files are in the [`src/main/resources/fr/jmmc/aspro/model`](https://github.com/JMMC-OpenDev/aspro-conf/tree/master/src/main/resources/fr/jmmc/aspro/model) folder:
- `AsproOIConfigurations.xml`:
- optical interferometers
  - `VLTI.xml`: ESO VLTI interferometer [Very Large Telescope Interferometer](https://www.eso.org/sci/facilities/paranal/telescopes/vlti.html)
  - `CHARA.xml`: CHARA interferometer [Center for High Angular Resolution Astronomy](http://www.chara.gsu.edu/)
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
