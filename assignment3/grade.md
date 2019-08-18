# cscc01 -- assignment 3

## information

- **graded by**: Kevin Zhang
- **email:** mstr.zhang@mail.utoronto.ca
- **remark policy:** consult email within 4 days of receiving mark

---

## grade overview

**TOTAL MARK**: 38/40

|component|mark|total|
|---|---|---|
|frontend|13|15|
|backend|20|20|
|structure|5|5|

**additional deductions**:

- none

**BONUS**:

- none

---

## frontend

|component|mark|total|
|---|---|---|
|frontend has required components|10|10|
|frontend has good user experience|1|3|
|frontend style is acceptable|2|2|

**additional comments**:

- crashes occassionally
    - going to the home page without crawling first causes a 500 error
- not much styling but acceptable

---

## backend

|component|mark|total|
|---|---|---|
|crawler works|10|10|
|some sort of database structure exists|1|1|
|database structure is adequate|2|2|
|database has some pre-populated entries|2|2|
|unit tests exist and are of good quality|3|5|

**additional comments**:

- database looks good
- unit tests exist but some fail
    - mainly assertion errors
- should be using mockito in some places where it isn't being used
    - e.g. should mock the testExtractAddress / textExtractData as opposed to using a hard-coded resource

---

## structure

|component|mark|total|
|---|---|---|
|using some sort of MVC architecture|3|3|
|no design flaws apparent|2|2|

**additional comments**:

- structure is good
