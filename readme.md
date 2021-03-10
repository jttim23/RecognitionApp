# Read Me First

## Table of contents
* [General info](#general-info)
* [Setup](#setup)
* [Run](#run)

## General info
<p>  This is app build as recruitment task it can detect gender by given name. 
<p>  Gender detection algorithm is fairly simple and is based on a list of female and male tokens,
 that are packaged within JAR, in the form of two flat files.
<p>  Each line contains a separate token. 
<p>  For each name, the app responds with gender ("MALE” , "FEMALE") or “INCONCLUSIVE” when not recognized.

#### Gender detection algorithm is implemented in two variants: 
<p> 1. Only first token of name is checked.
<p> 2. All tokens are checked and majority rule is used. Current implementation of this algorithm uses HashSet 
so it doesn't allow to send duplicated names e.g. "John Mary John" will respond with "INCONCLUSIVE" as it wil 
find only one male and one female token.

#### Application exposes two HTTP endpoints: 
<p>

1. **GET:** **/api/recognize** with query params *@RequestParam("name")* and *@RequestParam("algorithm")* for using the gender guessing with selected variant.

    * Parameter *'name'* is a single string for which gender recognition would be done e.g. "John Smith"

    * Parameter *'algorithm'* **must be** exactly the same as a name of chosen recognition strategy (letter case irrelevant) so for 2 created it is:
        * FIRST_NAME_ALGORITHM - for algorithm that check only first token of name

        * ALL_NAMES_ALGORITHM - for algorithm that check only all tokens of name
2. **GET:** **/api/lists** with query param *@RequestParam("gender")* for returning List of available tokens for each gender.
    * Parameter *'gender'* must be one of two(letter case irrelevant):
        * MALE - for list of male tokens
        * FEMALE - for list of female tokens


## Setup

<p>1. Please run git command in your destination folder:

`git clone https://github.com/jttim23/RecognitionApp.git`
<p>2. Change direction:

`cd RecognitionApp`
<p>3. Run maven command to create executable jar file.

`mvn package`
<p>4. Run fat-jar using command:

`java -jar target/recognitionApp-0.0.1.jar`
<p>   *Or just directly download jar file from github release and run:

`java -jar recognitionApp.jar`
<p>

## Run

<p>  You can run with additional command line args : 

`--males.path={path to male tokens} --females.path={path to female tokens}`
<p>  

**Default** path is */src/main/resources/static/maleTokens.txt*
 and */src/main/resources/static/maleTokens.txt* 
