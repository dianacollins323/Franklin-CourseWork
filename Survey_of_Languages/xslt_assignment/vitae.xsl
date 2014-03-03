<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" encoding="utf-8" indent="yes" />
<xsl:template match="ACADEMICVITAE">
    <html>
        <head>
            <link rel="stylesheet" type="text/css" href="vitae.css" />
        </head>
        <body>
            <h1>Kenny Gruchalla</h1>
            <h3>Academic Vitae</h3>
            <h2>Academic Degrees</h2>
            <xsl:for-each select="ACADEMICDEGREES/DEGREE">
                 <xsl:sort select="@YEAR" data-type="number" order="descending" />
                 <p class="firstline">
                     <xsl:value-of select="@YEAR"/>&#160;<xsl:value-of select="NAME"/>&#160;<xsl:value-of select="MAJOR"/>
                 </p>
                 <p class="subline"><xsl:value-of select="INSTITUTE"/>&#160;<xsl:value-of select="INSTITUTE/@ADDRESS"/></p>
            </xsl:for-each>
            <h2>Employment Experience</h2>
            <xsl:for-each select="EMPLOYMENTHISTORY/EMPLOYMENT">
                <xsl:sort select="JOB/@BEGINYEAR" data-type="number" order="descending" />
                <div>
                    <p class="firstline">
                        <xsl:value-of select="JOB/@BEGINMONTH"/>&#160;<xsl:value-of select="JOB/@BEGINYEAR"/> - <xsl:value-of select="JOB/@ENDDATE"/>
                        &#160;&#160;<xsl:value-of select="JOB/TITLE"/>
                    </p>
                    <p class="subline"><xsl:value-of select="EMPLOYER/NAME"/>, <xsl:value-of select="EMPLOYER/@ADDRESS"/></p>
                    <p class="subline">Responsibilities:&#160;<xsl:value-of select="JOB/RESPONSIBILITIES"/></p>
                </div>
            </xsl:for-each>
            <h2>Publications</h2>
            <xsl:for-each select="PUBLICATIONRECORD/PUBLICATION">
                <xsl:sort select="AUTHOR/@NUMBER" data-type="number" order="ascending" />
                <div>
                    <p class="firstline"><xsl:apply-templates select="AUTHORS/AUTHOR" /></p>
                    <p class="subline">
                        <xsl:value-of select="TITLE"/>&#160;<xsl:value-of select="PUBLISHER"/>&#160;
                        <xsl:value-of select="@YEAR"/>&#160;<xsl:value-of select="@VOLUME"/>&#160;
                        <xsl:value-of select="@NUMBER"/>
                    </p>
                </div>
            </xsl:for-each>
            <h2>Funded Research</h2>
            <xsl:for-each select="FUNDINGRECORD/PROJECT">
                <xsl:sort select="@BEGINYEAR" data-type="number" order="descending" />
                <div>
                        <p class="firstline"><xsl:value-of select="@BEGINYEAR"/>&#160;&#160;<xsl:value-of select="NAME"/></p>
                        <p class="subline"><xsl:value-of select="SPONSOR"/>, <xsl:value-of select="SPONSOR/@AMOUNT"/></p>
                        <p class="subline"><xsl:value-of select="ROLE"/></p>
                </div>
            </xsl:for-each>
            <p>
                <xsl:text>
                    Is XSLT a programming language?" Give your rationale. Note that
                    an answer to the effect that XSLT doesn't allow general computation is
                    not valid. In fact, XSLT has been demonstrated to be Turing complete.
                    Instead, focus on the way you have to think about coding in XSLT.
                </xsl:text>
            </p>
            <p>
                <xsl:text>
                    Yes, XSLT is a programming language. It is a text based language that 
                    matches patterns. I had difficulty fully understanding how to use XSLT, 
                    but with more time I'm confident it would be come clear.
                </xsl:text>
            </p>
            <p>
                <xsl:text>
                    What are the fundamental ways in which XSLT differs from the Java
                    programming language?
                </xsl:text>
            </p>
            <p>
                <xsl:text>
                    The way XSLT code is implemented. It uses tages to identify elements 
                    rather than variable assignments.
                </xsl:text>
            </p>
            <p>
                <xsl:text>
                    What are the primary abstractions supported by the XSLT language?
                </xsl:text>
            </p>
            <p>
                <xsl:text>
                    XSLT transforms xml documents into new xml documents, html, and plain text.
                </xsl:text>
            </p>
            <p>
                <xsl:text>
                    What is the underlying procedure implemented by the XSLT proces-
                    sor? That is, at a high level of description, what occurs when the
                    processor is presented with XSLT and XML documents?
                </xsl:text>
            </p>
            <p>
                <xsl:text>
                    The XSLT document is read, and the XML document it transformed into a new 
                    document according to the direction specified in the XSLT document.
                </xsl:text>
            </p>
        </body>
    </html>
</xsl:template>

<xsl:template match="AUTHOR">
	<xsl:value-of select="." />
	<xsl:if test="position() != last()"><xsl:text>, </xsl:text></xsl:if>
	<xsl:if test="position() = last()-1"><xsl:text>and </xsl:text></xsl:if>
	<xsl:if test="position() = last()"><xsl:text>.</xsl:text></xsl:if>
</xsl:template>

</xsl:stylesheet>