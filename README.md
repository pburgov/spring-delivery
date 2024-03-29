**Still under construction.**
## Spring Delivery  

This is a demo of a Spring Boot app to manage the deliveries of a company.


#### Login Page
When the app runs, the initial screen is the following login page

![Login_Image](./git_images/login_image.png)

Depending on the selected role, some features won't be available

#### Main Page

Once logged, the screen showed is the next one

![Main Page](./git_images/main_page.png)

The column ***Map*** will be available only to the role **admin**.

Clicking on the icon of the map, the details of the delivery will be shown below the map.
The map will display the geographic delivery points.

![Delivery Detail](./git_images/delivery_detail.png)

The button ***Details*** will switch the **Map** height.

![Whole Map](./git_images/whole_map.png)

#### Pictures

To see the pictures relative to a particular delivery, click on the number of the details table or on a **marker** of the Map.

I will appear a modal dialog with the photos.

![Pictures Modal](./git_images/pictures_modal.png)

#### Available Data 

The available dates to be selected are those between the 01/08/2019 and 31/08/19.

Any date selected out of that range will raise an error

![Out Of Range Error](./git_images/out_of_range_error.png)
 
If no date is selected the following will be shown 
 
![Must Select Date Error](./git_images/must_select_date_error.png)


If there are no deliveries for a particular date (E.g. Bank Holiday -> 15/08/2019)... 
 
![Bank Holiday Error](./git_images/bank_holiday_error.png)

**Pd: No fruit or vegetable were harmed in the development of this app**
