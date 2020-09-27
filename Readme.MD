# NDA calculator

Calculator takes XML files which contain math expressions, makes calculation and saves results in XML files.

## Installation

Go to NdaCalculator project

```bash
mvn clean install
```

## Usage

Go to target folder

```python
java -jar NdaCalculator-1.0-SNAPSHOT-jar-with-dependencies.jar "path/to/input/data" "path/to/output/data"

```
For example on Windows:
```
java -jar NdaCalculator-1.0-SNAPSHOT-jar-with-dependencies.jar "c:/input" "c:/output"
```

## Important
Input and output path must exist!
