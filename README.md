# Eat

## Building

## EatApi Submodule

## Iteration One
What's up Damevski? As you know, our app intends to provide a service where customers can order food from a restaurant and have it ready at the table when they arrive. For this first iteration we focused on creating a home screen that listed nearby restaurants, allowing restaurant owners to add restaurants to a database, and displaying information for the restaurants (location, type of food, hours, etc) to the user.
__Nearby Restaurants:__ This is the "home" screen of the app, it lists restaurants closest to you and allows basic navigation through the use of a slide-in menu. Currently there is 1 dummy restaurant for demonstrative purposes.
* realized implementing location services was too much for just one iteration and split it into 2 user stories so the feature was put on ice for this iteration
* focused on the layout, view, and pulling from the database instead
__Adding New Restaurants:__ In order to display restaurants to the user, we have to allow restaurant owners to add their restaurants into the database. We created an activity where they can input their location, type of food, picture of their restaurant, and select they're operating hours.
* Resolved issues with the EditText keyboard popping up upon creation, causing issues in testing
* Focused on user interaction, especially when choosing the hours of operation
__Viewing Restaurant Details:__ After the user finds a restaurant in the list, they can click on it to bring it to a detailed page of what the restaurant is like, their location, a description, and an option to see a menu.
* After realizing menu input was a large undertaking, we decided to focus more on user experience and making the app layout more smooth
* The menu input is now relocated to another iteration

After creating (fumbling through) acceptance tests for each of the scenarios, we realized that tests couldn't be written as easily as expected and required out-of-the-box thinking for some, while we completely over thought others. In the end we collectively have 10 tests that run and pass(sometimes when they feel like it).
All together, we have a nice skeleton for our app and we hope to give it more functionality and real data in the following iterations.

## Iteration Two
In the second iteration we decided to implement the login, user signup and user profile stories. They are all related, which allowed us to focus on the functionality and testing in greater detail. Not being distracted by development of different aspects of the app and having a basic structure already built from the first iteration allowed us to be more precise. This resulted in higher quality code, better test coverage as well as increased code readability.
__Login:__ Through the slide-in menu users of both kinds - customers and restaurant owners - are able to log in either through a custom account created just for this app's usage purposes or through a third party service account, such as Google. 
* We looked at both Firebase and Google API during the process of implementation in order to utilize existing frameworks and not reinvent the wheel. 
* Users can now send a reactivation link for their password, and are alerted when they enter an incorrent password.
__User Signup:__ User signup works analogously. It is a part of the user identification menu that is available through the slide-in bar for the app users. 
* While Google account works very similarly in the signup and login, registration (signup) for solely app's usage purposes needed to be implemented in greater detail. 
* We only require minimal amount of information for the user to be signed up so that we incentivize joining the community.
__User Profile:__ Apart from the necessary information for the user to create an account of their own, we allow them to input various kinds of information necessary for ordering the right type of food to the right place and that they can be contacted on the right phone number etc. 
* We allow the user to get to their profile from any screen in the app through the sidebar slide-in menu.
* The app can notify the user of incorrect information in their user profile and then guides them through the process of modifying this information.

During this iteration we managed to focus on making our first iteration code better through refactoring. After that we made sure that the aspect of user profiles, login and sign up was implemented properly, as it is vital for our app's mission that the user experience is personalized and that the issues with seeing one's own information and data are minimized. 
The next iteration will allow us to chip off of our list of stories that haven't gotten any fewer due to us splitting a few during the course of the first two iterations. Thanks to this process, however, we are now able to work on these aspects that are represented by the stories with more attention to detail and more rigorously.

