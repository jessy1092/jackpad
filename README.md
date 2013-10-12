#Overview

[![Build Status](https://travis-ci.org/jessy1092/jackpad.png?branch=master)](https://travis-ci.org/jessy1092/jackpad)

A client library for the Hackpad API (Version 1.0)

Make sure to check out the official [Hackpad API documentation](https://hackpad.com/Hackpad-API-v1.0-k9bpcEeOo2Q).

#Basic Usage

##JackpadClient

``` java
JackpadClient jackpadClient = new JackpadClient(HACKPAD_CLIENT_ID, HACKPAD_SECRET);
jackpadClient.build();
```

##Creat Pad

``` java
Pad pad = new Pad("text/plain", "Create Pad");
pad.setTitle("Hello");
jackpadClient.createPad(pad);
```

##Get Pad Content

``` java
String padText = jackpadClient.getPadContent(PADID, "latest", "html");
```

##Update Pad Content

``` java
Pad pad = new Pad("text/plain", "Update Pad");
pad.setTitle("Hello");
pad.setPadID(PADID);
boolean test = jackpadClient.updatePadContent(pad);
```

#Maven

##Running Test

`mvn test`

##Installation

`mvn install`

#Reporting issues

GitHub issue tracker: https://github.com/jessy1092/jackpad/issues

#License

The code is available at github project under [MIT License](https://github.com/jessy1092/jackpad/blob/master/LICENSE)

