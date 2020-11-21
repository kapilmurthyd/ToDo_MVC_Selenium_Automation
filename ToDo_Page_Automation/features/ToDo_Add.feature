Feature: Test various features of the ToDo list page

  Background: 
    Given The ToDo MVC List Page is launched

  Scenario Outline: Add items to ToDo list
    Given The ToDo List Page is open
    When I add the "<item>" to the list
    Then I verify the "<item>" has been added in the list

    @ENV2
    Examples: 
      | item     |
      | ABC      |
      |      123 |
      | Abc123   |
      | 123Abc   |
      | E^%&%$() |
      | Abc123   |

  Scenario Outline: Delete items from ToDo list having Multiple Items
    Given The ToDo List Page is open
    And I add the "<addItems>" to the list
    When I remove the "<deleteItems>" from the list
    Then I verify the "<deleteItems>" has been removed from the list

    @ENV1
    Examples: 
      | addItems                 | deleteItems |
      | ABC, 123, Abc123, 123Abc | 123Abc, ABC |

  Scenario Outline: Modify items on the ToDo list
    Given The ToDo List Page is open
    And I add the "<addItems>" to the list
    When I modify the "<modifyItems>" on the list
    Then I verify the "<modifyItems>" has been modified on the list

    @ENV2
    Examples: 
      | addItems                 | modifyItems                  |
      | ABC, 123, Abc123, 123Abc | 123Abc as 456Def, ABC as XYZ |

  Scenario Outline: Mark items as Completed in ToDo list having Multiple Items
    Given The ToDo List Page is open
    And I add the "<addItems>" to the list
    When I Mark the "<completedItems>" on the list
    Then I verify the "<completedItems>" has been crossed on the list

    @ENV1
    Examples: 
      | addItems                           | completedItems   |
      | ABC, 123, 123Abc, Abc123, 456, ABC | ABC, Abc123, 456 |

  Scenario Outline: Check functionality of Active button in the ToDo list
    Given The ToDo List Page is open
    And I add the "<addItems>" to the list
    And I Mark the "<completedItems>" on the list
    When I click on the Active button in the ToDo list
    Then Only the Active items in the list must be displayed

    @ENV1
    Examples: 
      | addItems                           | completedItems   |
      | ABC, 123, 123Abc, Abc123, 456, ABC | ABC, Abc123, 456 |

  Scenario Outline: Check functionality of Completed button in the ToDo list
    Given The ToDo List Page is open
    And I add the "<addItems>" to the list
    And I Mark the "<completedItems>" on the list
    When I click on the Completed button in the ToDo list
    Then Only the Completed items in the list must be displayed

    @ENV1
    Examples: 
      | addItems                           | completedItems   |
      | ABC, 123, 123Abc, Abc123, 456, ABC | ABC, Abc123, 456 |

  Scenario Outline: Check functionality of Clear Completed button in the ToDo list
    Given The ToDo List Page is open
    And I add the "<addItems>" to the list
    And I Mark the "<completedItems>" on the list
    When I click on the Clear-Completed button in the ToDo list
    Then Only the Active items in the list must be displayed
    And The Clear-Completed button is not displayed

    @ENV1
    Examples: 
      | addItems                           | completedItems   |
      | ABC, 123, 123Abc, Abc123, 456, ABC | ABC, Abc123, 456 |

  Scenario Outline: Mark items as Completed in ToDo list having Single Item
    Given The ToDo List Page is open
    And I add the "<addItems>" to the list
    When I Mark the "<completedItems>" on the list
    Then I verify the "<completedItems>" has been crossed on the list

    @ENV1
    Examples: 
      | addItems | completedItems |
      | Abc123   | Abc123         |

  Scenario Outline: Delete item from ToDo list having Single Item
    Given The ToDo List Page is open
    And I add the "<addItems>" to the list
    When I remove the "<deleteItems>" from the list
    Then I verify the "<deleteItems>" has been removed from the list
    And Verify All, Active and Completed buttons are not displayed

    @ENV1
    Examples: 
      | addItems | deleteItems |
      | Abc123   | Abc123      |
