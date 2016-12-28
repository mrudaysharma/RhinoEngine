This version implemented using Rhino engine

This document describes the basic information about SightlyProject

Requirement 

Netbeans 7.1 or later
JDK 1.7 both compile and source

How to access Service?

Servlet URL path : http://localhost:8080/Sightly/parser?id=4

This URL with parameter ID = 4 Produce output as per below

Maria

Spouse: Pere

Child: Anna
Child: Berta
Child: Clara

HTML file URL path : http://localhost:8080/Sightly (already included in code)


Components 

ClientBridge -- Who provide and run factories

--Factory Pattern
	ScriptFactory -- Process script elements and creat object of Class Person

	AttributeFactory -- Parse html attributes and pass this attributes to DataDriven Factory

--State Pattern

	DataDrivenFactory -- Fetch data-for and data-if contents from html and generate data for it and later combine into html form
	DataEmptyState
	DataIfState
	DataForState



