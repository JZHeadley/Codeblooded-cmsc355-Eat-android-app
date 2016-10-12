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
