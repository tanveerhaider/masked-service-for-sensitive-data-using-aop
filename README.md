# Spring AOP Example to mask sensitive data
<p align="left">
 
This project demonstrates to create custom annotation to mask sensitive data
on API response.

Here I have created @MaskedService annotation to annotate RestControllers
to mask Data on the DTO annotated with @MaskedField .

The data masking logic masks all data on the field except last four as an illustration. This
can be enhanced as per project requirements.