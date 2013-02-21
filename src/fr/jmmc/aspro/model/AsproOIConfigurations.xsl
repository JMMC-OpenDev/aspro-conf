<?xml version="1.0" encoding="UTF-8"?>
<!--
 *******************************************************************************
 * JMMC project ( http://www.jmmc.fr ) - Copyright (C) CNRS.
 *******************************************************************************
 -->
<!--
    Document   : AsproOIConfigurations.xsl   
    Author     : mella
    Description:
        Generate an html overview of configurations handled by Aspro2.
-->

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"     
    xmlns:a='http://www.jmmc.fr/aspro-oi/0.1'   
    version="1.0">
    <xsl:output method="html"/>
    
    <xsl:template match="/">
        <html>
            
            <head>
                <title>Aspro2 Instrumental Configurations</title>
                <link rel="stylesheet" href="http://www.jmmc.fr/css/2col_leftNav.css" type="text/css" />
            </head>
            
            <body>  
                
                <div id="content">
                    <h1>
                        <a name="top"/>List of interferometers with their instruments
                    </h1>                
                    <p>
                        The following information is extracted from the ASPRO2 configuration. Please send us your remark if anything seems wrong.
                        <br/>
                        <a href="http://www.jmmc.fr/support">http://www.jmmc.fr/support</a>
                        <br/>
                        You can get more documentation from our generated schema documentation:
                        <br/>
                        <a href="http://www.jmmc.fr/aspro-oi/0.1">http://www.jmmc.fr/aspro-oi/0.1</a>
                    </p>
                    <br/>                
                    <br/>                                                                      
                    <xsl:apply-templates mode="content" select="/a:configurations/interferometerFile/file"/>                                        
                </div>  
                                        
                <div id="navBar">
                    <div id="sectionLinks" class="Style13">                         
                        <xsl:apply-templates mode="navBar" select="/a:configurations/interferometerFile/file"/>                                                    
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="/a:configurations/interferometerFile/file" mode="content">
        
        <xsl:variable name="interferometerSettingFilename" select="."/>                      
        <xsl:variable name="interferometerSetting" select="document($interferometerSettingFilename)/a:interferometerSetting"/>   
        <xsl:variable name="interferometerName" select="$interferometerSetting/description/name"/>
        
        <hr/>                 
                   
        <h1>            
            <em>
                <xsl:value-of select="$interferometerName"/>
            </em> interferometer 
            <a name="interferometer_{$interferometerName}"/>(
            <a href="#top">top</a>)
        </h1>            
        
        <p>
            <!-- use for-each to simplify xpath under description-->
            <xsl:for-each select="$interferometerSetting/description">
                <u>Description:</u>
                <xsl:copy-of select="description"/>
                <br/>            
                <u>Geocentric coordinates [m]:</u>
                <em>
                    <xsl:value-of select="concat(' ',position/posX,', ',position/posY,', ',position/posZ)"/>
                </em>
                <br/>
                
                <u>Telescope(s):</u>                
                <xsl:for-each select="telescope">
                    <xsl:variable name="telescopeName" select="name"/>
                    
                    <ul>
                        <li>
                            <u>Key Name: </u>
                            <xsl:value-of select="name"/>
                            
                        </li>
                        <ul>
                            <li>
                                <u>Diameter [m]: </u>
                                <xsl:value-of select="diameter"/>
                            </li>
                            <li>
                                <u>Stations: </u>
                                <xsl:for-each select="$interferometerSetting/description/station[telescope=$telescopeName]">
                                    <xsl:value-of select="concat(' ', name)"/>
                                </xsl:for-each>                    
                            </li>
                            
                            <li>
                                <u>Max elevation [deg]: </u>
                                <xsl:value-of select="maxElevation"/>
                            </li>
                            <li>
                                <u>Adaptive Optics: </u> 
                                <xsl:for-each select="adaptiveOptics/*">
                                    <xsl:value-of select="concat(' ', name(), '=', .)"/>
                                </xsl:for-each>
                            </li>      
                        </ul>
                    </ul>                                                    
                </xsl:for-each>      
                
                <br/>
                <u>Delay lines [m]:</u>                
                <xsl:for-each select="delayLine">
                    <xsl:value-of select="concat(' ', name ,' (', maximumThrow, ')')"/>
                </xsl:for-each> 
                
                <br/>
                <u>Fringe tracker:</u>                
                <ul>
                    <!-- could be explicit to describe name band mode...-->
                    <xsl:for-each select="fringeTracker/*">
                        <li>
                            <u>
                                <xsl:value-of select="concat(name() ,': ')"/>
                            </u>
                            <xsl:value-of select="."/>
                        </li>
                    </xsl:for-each>
                </ul>                                                   
            </xsl:for-each>  
                                            
        </p>
              
                
                    
        <xsl:for-each select="$interferometerSetting/description//focalInstrument">
            <xsl:variable name="instrumentName" select="name"/>
            <h2>
                <a name="instrument_{$instrumentName}_{$interferometerName}"/> 
                <em>
                    <xsl:value-of select="$instrumentName" />
                </em> focal instrument (
                <a href="#top">top</a>)
            </h2>      
            <p> 
                <table>
                    <tr>                        
                        <td valign="top">
                            <div class="coloredtable centered">                                                        
                                <table >                                                                    
                                    <!-- could be explicit to describe name, description, numberChannels...-->              
                                    <xsl:for-each select="*[not(*)]">
                                        <tr>
                                            <td>
                                                <xsl:value-of select="name()"/>:
                                            </td>
                                            <td>
                                                <xsl:value-of select="."/>
                                            </td>                                
                                        </tr>                                                   
                                    </xsl:for-each>
                                </table>
                            </div>
                        </td>
                        <td valign="top">
                            <!-- modes -->
                            <div class="coloredtable centered">
                                <table>
                                    <tr>
                                        <th>mode</th> 
                                        <th>resolution</th>
                                        <th>wlen min</th>
                                        <th>wlen max</th>
                                        <xsl:if test="mode/parameter">
                                            <th>used by OB export</th>
                                        </xsl:if>
                                    </tr>
                                    <xsl:for-each select="mode">
                                        <tr> 
                                            <td>
                                                <xsl:value-of select="name"/>:
                                            </td>
                                            <td>
                                                <xsl:value-of select="resolution"/>
                                            </td>
                                            <td>
                                                <xsl:value-of select="waveLengthMin"/>
                                            </td>
                                            <td>
                                                <xsl:value-of select="waveLengthMax"/>
                                            </td>   
                                            <xsl:if test="parameter">
                                                <td>
                                                    <xsl:for-each select="parameter">
                                                        <xsl:value-of select="concat(name, '=', value,' ')"/>
                                                    </xsl:for-each>
                                
                                                </td>
                                            </xsl:if>                     
                                        </tr>                                             
                                    </xsl:for-each>
                                </table>
                            </div> 
                        </td>                        
                    </tr>
                </table>
                
                                        
            </p>            


            <h2>Offered configurations for 
                <xsl:value-of select="$instrumentName"/>
            </h2>   
            <xsl:variable name="configurations" select="$interferometerSetting/configuration[instrument/focalInstrument=$instrumentName]"/>
            <xsl:choose>
                <xsl:when test="$configurations[version]">                    
                    <xsl:for-each select="$configurations">                
                        <h3>
                            <xsl:value-of select="version" /> period
                        </h3>     
                        <p>
                            <xsl:for-each select="instrument[focalInstrument=$instrumentName]/configuration/stations">                       
                                <xsl:value-of select="concat(' (', ., ')')"/>
                            </xsl:for-each>      
                        </p>   
                    </xsl:for-each>        
                </xsl:when>
                <xsl:otherwise>
                    <xsl:for-each select="$configurations/instrument[focalInstrument=$instrumentName]/configuration/stations">                       
                        <xsl:value-of select="concat(' (', ., ')')"/>
                    </xsl:for-each>                                
                </xsl:otherwise>
            </xsl:choose>                         

                                                
        </xsl:for-each>             
    </xsl:template>

    
    <!-- Generate the top left menu entry on top of www.jmmc.fr main css-->        
    <xsl:template match="/a:configurations/interferometerFile/file" mode="navBar">
        
        <xsl:variable name="interferometerSettingFilename" select="."/>                      
        <xsl:variable name="interferometerSetting" select="document($interferometerSettingFilename)/a:interferometerSetting"/>   
        <xsl:variable name="interferometerName" select="$interferometerSetting/description/name"/>

        <li class="Style14">
            <a href="#interferometer_{$interferometerName}">
                <xsl:value-of select="$interferometerName"/>
            </a>
            <div class="Style20" align="left">          
                <xsl:for-each select="$interferometerSetting/description//focalInstrument">
                    <xsl:variable name="instrumentName" select="name"/>
                    <a href="#instrument_{$instrumentName}_{$interferometerName}">
                        <xsl:value-of select="$instrumentName"/>
                    </a>
                    <br/>
                </xsl:for-each>   
            </div>  
        </li>        

    </xsl:template>

</xsl:stylesheet>
