# Curves DSL, test project for JetBrains 2021 internship program

This project implements a dsl to easily
declare and draw curves described with polynomial
equations

It supports minor style adjustments such as individual
line color, width and transparency (alpha)

### How to run

The entry point will always be the ***curves***
function

Then the resulting object can be inspected manually or plotted by passing it into ***drawCurves()*** function

```kotlin
val result = curves {
  curve {
    range = 0..7
    x = t * 0
    y = t
  }
  curve {
    range = -1..1
    x = 3 * (1 - t * t)
    y = 4 + (t + 1) * 3 / 2
  }
  curve {
    range = -2..2
    x = 4 - t * t
    y = t + 2
  }
}
drawCurves(result)
```

![Basic example output](/img/example.png)

The example is already stored in Main.kt

To run the program use

    ./gradlew run
  
  
To run the tests use

    ./gradlew test
    
    
Inside the ***curves*** function new curves can be creating by invoking ***curve*** as shown in the example

The following arguments are expected for any curve and do not have default parameters:

* ***range*** - an IntRange, does not accept floating poing numbers

* ***x*** - a function to calculate the x coordinate. Must be provided in terms of ***t*** (global constant)

* ***y*** - a function for the y coordinate, follows the same rules

Optional arguments:

* ***color*** - String, specifies the color of a given curve
* ***size*** - Double, determines the thickness of a given curve
* ***alpha*** Double, determines the transparency. 0.0 for transparent, 1.0 for solid

```kotlin
val curves = curves {
        curve {
            range = 0..7
            x = t * 0
            y = t
            color = "blue"
        }
        curve {
            range = -1..1
            x = 3 * (1 - t * t)
            y = 4 + (t + 1) * 3 / 2
            size = 6.0
        }
        curve {
            range = -2..2
            x = 4 - t * t
            y = t + 2
            alpha = 0.3
        }
    }
    drawCurves(curves)
```

![Colored](/img/example.png)