import model.DataMenu
import model.Order


class Resto {
    val foodmenu = listOf(
        DataMenu(
            "Ayam Bakar", 50000
        ), // menu 1
        DataMenu(
            "Ayam Goreng", 40000
        ),// menu 2
        DataMenu(
            "Ayam Geprek", 40000
        ), // menu 3
        DataMenu(
            "Kulit Ayam Crispy", 15000
        ),// menu 4
        DataMenu(
            "Sate Usus Ayam", 5000
        ) //menu 5
    )

    class deliveryOrder {
        fun takeAway() {
            println("Your food is being cooked...")
            Thread.sleep(5000)
            println("Your food is ready! Please take it at the restaurant, OK?")
            Thread.sleep(3000)
            println("Order complete!")
        }

        fun delivery() {
            println("Your food is being cooked...")
            Thread.sleep(5000)
            println(
                "\n" +
                        "Your food is ready! Driver will come to your place immediately!"
            )
            Thread.sleep(3000)
            println("Driver arrived! Order complete!")
        }
    }

    fun showMenu() {
        println("List Food Menu:")
        println("===============================")
        println("nurul aisyah")
        println("===============================")
        foodmenu.forEachIndexed { index, menu ->
            println("${index + 1}. ${menu.nameMenu} = Rp ${menu.priceMenu}/portion")
        }
    }
    fun orderMenu(numberMenu: Int, quantity: Int): Order? {
        if (numberMenu in 1..foodmenu.size) {
            val selected_Menu = foodmenu[numberMenu - 1]
            return Order(selected_Menu, quantity)
        }
        return null
    }

    fun payment(Order: Order, payment: Int): Boolean {
        val totalPrice = Order.menu.priceMenu * Order.OrderQuantity
        return payment >= totalPrice
    }

    fun deliveryService(): deliveryOrder {
        return deliveryOrder()
    }

}

fun main (){
    val Resto = Resto()
    Resto.showMenu()

    println("Select Food Menu 1/2/3/4/5 :   ")
    val numberMenu = readLine()?.toIntOrNull() ?: return

    println("number of servings : ")
    val quantity = readLine()?.toIntOrNull() ?: return
    val Order = Resto.orderMenu(numberMenu, quantity)
    if (Order != null) {
        println("you order : ${Order.OrderQuantity} portion ${Order.menu.nameMenu}")

        print("input payment: ")
        val pay = readLine()?.toIntOrNull() ?: return

        if (Resto.payment(Order, pay)) {
            println("payment succes!.")

            println("Choice of Food Delivery Method:")
            println("1. Take Away")
            println("2. Delivery")
            print("Choice of Food Delivery Method 1/2: ")
            val yourChoiceOfMethod = readLine()?.toIntOrNull() ?: return

            val pengiriman = Resto.deliveryService()
            when (yourChoiceOfMethod) {
                1 -> {
                    println("your choise: Take Away")
                    pengiriman.takeAway()
                }
                2 -> {
                    println("your choise: Delivery")
                    pengiriman.delivery()
                }
                else -> println("Invalid method selection.")
            }
        } else {
            println("Sorry, your payment is insufficient.")
        }
    } else {
        println("Invalid input or menu not found.")
    }
}

