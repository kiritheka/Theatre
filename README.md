# BookMyShow

#### BookingTickets

Bookmyshow where you can view  movies running in near by theatre ,view prices for each show and  book ticket for a particular show for a wished seating type and can view the total revenue generated in a particular theatre for each show.

#### SeatingType
Type: `enum` 
Theatre contains different seatingArrangements with different price tags
#### Show
Type: `object` 
Show contains specific show name with timing and movie corresponding to a particular Theatre
#### Theatre
Type: `object`
Theatre contains specific name to it with maximum seating capacity with different seating types

### Methods
#### .bookTicketForShow()
Type: `function`
Inputs: `SeatingType`
Generates ticket number for particular show and seating type after checking availability of seats for a show.
Returns: ticketnumber

#### .getListOfShowsForMovie()
Type: `function`
Inputs: `movie name`
get list of all shows in available theatres with a given movie screening in it.
Returns: ticketnumber

#### .getTotalRevenueForShow()
Type: `function` 
Inputs: `show`
Computes total revenue generated for a particular theatre for each show.
Returns: totalrevenue


#### USERS
* Admin
  - getTotalRevenueForShow()
* Customer
  - getListOfMoviesRunning()
  - getListOfShowsForMovie()
  - bookTicketForShow()
