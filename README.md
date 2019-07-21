# PokerCalculator
Texas Hold'em helper that evaluates the odds using Monte Carlo method.

## Features
The app will return results based on the board information from your inputs, and prints out the probabilities of the highest hand that can be formed from your hole cards and community cards.

Future plans: 
- add caluation that simulates opponents hand and chances of you winning.
- add equity calculator that suggests bets based on odds.
## Usage 
Example input: 
(Your hand is Ace of Spades, 5 of Clubs at pre-flop)
```
- 1s/5c/ 
```
Example output of 100,000 simulation:
```
Highest Hand    Percent%   Count 
---------------------------------- 
ROYAL_FLUSH      0.00        2 
STRAIGHT_FLUSH   0.02       15 
FOUR_OF_A_KIND   0.16      156 
FULL_HOUSE       2.16     2161 
FLUSH            1.89     1893 
STRAIGHT         4.37     4368 
THREE_OF_A_KIND  4.41     4410 
TWO_PAIR        22.85    22846 
PAIR            45.11    45105 
HIGH_CARD       19.04    19044 
NONE             0.00        0 
-                       100000 
```
Which matches nicely with the probability of 7 card poker hands.

### References
MIT Data Science - Monte Carlo Simulation by Prof. Guttag
- https://www.youtube.com/watch?v=OgO1gpXSUzU&t=0s

Poker Probability of 7 cards - Wikipedia
- https://en.wikipedia.org/wiki/Poker_probability#Frequency_of_7-card_poker_hands
