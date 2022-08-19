Converted Android Gradle from 7.3.3 to 7.4.2

1. Change `gradle-wrapper.properties` gradle version to 7.4.2
2. Remove all 7.3.3 folder from Android Studio:
   `buildSrc` > `.gradle` > `7.3.3`
   `gradle` > `7.3.3`

Run `./gradlew detekt` before every commit

Error: The app for your currently selected variant (Unknown output) is not signed. Please specify a signing configuration for this variant (debug).

`Note` Error occurs while building release version but solution works.
`Important` Not sure 100% working solution as key alias and key password pending

1. Go to `File menu` > `Project Structure`
2. Then go to `Module Section`
3. Then head to `Default Config` and scroll down to `SigningConfig`
4. Then select `$signingConfigs.debug` from drop down list.
