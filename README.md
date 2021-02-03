# Demo-Password-Encoder
This code is for educational purposes and implements password encoder using SHA-512 + salt.

## Contains the PasswordEncoder class, which contains methods
```java
public String encode(String password)
public String encode(String password, int level)
public boolean matches(String encodedPassword, String password)
```
- password - your password
- encodedPassword - your encoded password
- level - re-encoding amount (min - 1; default - 8)

## Result encoded example:
```
8$eeedb2f12bc4e39b503f81ead7cd8a39$3a42f7804b9bc0d77c5f024821b8eb2209eefbd7d869c25c59d4757464b4ade489eeb957a76c2c2557f6ab7294075ee4f34a97ea941d040f2616a2857140f92c
5$5b22464ee32e69146af29c42228bc32$bfa855e02259ad90aba787a01b57dc28261251b3ece687be551140d03786c916b6dab338e77987307c2ef7c1899ade3f55e8ce61654c0c290ecc03408e94065d

re-encoding amount $ salt $ encoded password
```
