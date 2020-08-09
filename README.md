# spring-security-boilerplate

Spring Security is crazy complicated. The [docs](https://docs.spring.io/spring-security/site/docs/current/reference/pdf/spring-security-reference.pdf) are 550 pages long.
This is an attempt to figure out what in the world is going on.

## UserDetalService
A DAO for user data. Not a part of authentication - you fool.

## Granted Authorities vs. Roles
Why would anyone choose granted authorities over role based authorization?
  - This took me a while. Granted authority is much more granular than roles. Instead of saying anyone with the role ADMIN can post a new book. We are saying anyone with the 
  authority post_book can post a new book. This seems dumn, overly granular, and over engineered. But I was wrong.
  - In the legal world, legal secretarys do not have many authorities compared to a senior partner. They have a few, and it makes sence to give them the role LEGAL_SECRETARY that would encompass all of their authorities. But if one legal secretary could do things that others could not, say Donna was permitted to be promoted to COO, then it doesn't make sense to make a new role just for her with that one extra authority. Instead we should continue to give Donna the role of LEGAL_SECRETARY but add to her the one extra authority of 
  PROMOTE_COO.
