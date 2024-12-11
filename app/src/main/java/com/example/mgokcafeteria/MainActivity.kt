@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mgokcafeteria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mgokcafeteria.ui.theme.MGOKCafeteriaTheme

// Обновленная структура MenuItem с category_id
val menuItems = listOf(
    MenuItem("Поке с курицей и сыром", "poke_1", 450.00, 8.916, 6.216, 162.492, 120, 450, "Мясо кур (п/ф бедро, голень, грудка) с/м, Паприка сладкая сухая, Масло растительное, Соль, Помидоры, Огурцы свежие, Салат Айсберг, Сыр с м.д.ж. 45%, Крупа Булгур, Соль", 1),
    MenuItem("Салат-коктейль из овощей", "salad_1", 150.00, 1.02, 13.656, 76.68, 120, 150, "Салат Айсберг, Кукуруза консервированная, Огурцы свежие, Помидоры", 2),
    MenuItem("Салат Греческий с брынзой", "salad_2", 200.00, 3.768, 3.8, 109.92, 120, 200, "Сыр Брынза, Огурцы свежие, Перец сладкий свежий, Помидоры, Оливки консервированное, Салат Романо, Базилик сушёный", 2),
    MenuItem("Масло растительное", "oil", 30.00, 0.0, 0.0, 44.95, 5, 30, "Масло растительное", 3),
    MenuItem("Соевый соус", "soy_sauce", 25.00, 0.03, 0.205, 2.65, 5, 25, "Соус соевый", 3),
    MenuItem("Томатный соус", "tomato_sauce", 35.00, 0.15, 0.894, 5.85, 5, 35, "Томат-паста, Масло растительное, Сахар-песок, Орегано, Соль", 3),
    MenuItem("Шашлычки куриные по-азиатски", "chicken_skewers", 550.00, 38.805, 1.905, 542.325, 150, 550, "Мясо кур (п/ф бедро, голень, грудка) с/м, Соус соевый, Чеснок свежий, Куркума, Имбирь сухой, Кориандр, Соль, Масло растительное, Молоко кокосовое", 1),
    MenuItem("Сок яблочный", "apple_juice", 80.00, 0.2, 20.2, 92.0, 200, 80, "Сок яблочный", 4),
    MenuItem("Фрукты свежие в ассортименте", "fresh_fruits", 120.00, 0.3, 9.4, 45.67, 100, 120, "Яблоки свежие, Апельсин, Груши свежие", 5),
    MenuItem("Круассан", "croissant", 100.00, 30.58, 37.92, 452.1, 80, 100, "Круассан в ассортименте 1/80", 5),
    MenuItem("Хлебная корзина", "bread_basket", 150.00, 1.61, 31.891, 159.0, 60, 150, "Хлеб пшеничный в нарезке, Хлеб ржано-пшеничный в нарезке, Булочка Столичная", 6)
)

data class MenuItem(
    val name: String,
    val imagePath: String,
    val price: Double,
    val proteins: Double,
    val fats: Double,
    val carbs: Double,
    val calories: Int,
    val weight: Int,
    val ingredients: String,
    val categoryId: Int
)

data class Category(
    val id: Int,
    val name: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MGOKCafeteriaTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "menu") {
                    composable("menu") {
                        MainScreen(navController = navController)
                    }
                    composable("details/{itemIndex}") { backStackEntry ->
                        val itemIndex = backStackEntry.arguments?.getString("itemIndex")?.toIntOrNull() ?: 0
                        val menuItem = menuItems[itemIndex]
                        DetailsScreen(menuItem = menuItem, onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    var selectedCategory by remember { mutableStateOf(0) } // Категория по умолчанию "Все"

    val categories = listOf(
        Category(0, "Все"), // Новая категория "Все"
        Category(1, "Основные блюда"),
        Category(2, "Салаты и закуски"),
        Category(3, "Соусы и приправы"),
        Category(4, "Напитки"),
        Category(5, "Фрукты и десерты"),
        Category(6, "Хлеб и выпечка")
    )

    // Фильтрация блюд по categoryId
    val filteredMenuItems = if (selectedCategory == 0) {
        menuItems // Все блюда, если выбрана категория "Все"
    } else {
        menuItems.filter { it.categoryId == selectedCategory }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                text = "МГОК Сто́ловая",
                fontSize = 24.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Панель категорий с горизонтальной прокруткой
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories.size) { index ->
                    CategoryCard(category = categories[index]) {
                        selectedCategory = categories[index].id
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Сетка для отображения фильтрованных блюд
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(filteredMenuItems.size) { index ->
                    MenuItemCard(menuItem = filteredMenuItems[index]) {
                        navController.navigate("details/${menuItems.indexOf(filteredMenuItems[index])}")
                    }
                }
            }
        }
    }
}

@Composable
fun MenuItemCard(menuItem: MenuItem, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center
            ) {
                val imageResId = when(menuItem.imagePath) {
                    "poke_1" -> R.drawable.poke_1
                    "salad_1" -> R.drawable.salad_1
                    "salad_2" -> R.drawable.salad_2
                    "oil" -> R.drawable.oil
                    "soy_sauce" -> R.drawable.soy_sauce
                    "tomato_sauce" -> R.drawable.tomato_sauce
                    "chicken_skewers" -> R.drawable.chicken_skewers
                    "apple_juice" -> R.drawable.apple_juice
                    "fresh_fruits" -> R.drawable.fresh_fruits
                    "croissant" -> R.drawable.croissant
                    "bread_basket" -> R.drawable.bread_basket
                    else -> R.drawable.placeholder
                }

                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = menuItem.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = menuItem.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${menuItem.price} ₽",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .background(Color.LightGray)
            .padding(12.dp)
    ) {
        Text(
            text = category.name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun DetailsScreen(menuItem: MenuItem, onBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = onBack) {
                Text("Назад")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Фотография блюда вверху экрана
            val imageResId = when(menuItem.imagePath) {
                "poke_1" -> R.drawable.poke_1
                "salad_1" -> R.drawable.salad_1
                "salad_2" -> R.drawable.salad_2
                "oil" -> R.drawable.oil
                "soy_sauce" -> R.drawable.soy_sauce
                "tomato_sauce" -> R.drawable.tomato_sauce
                "chicken_skewers" -> R.drawable.chicken_skewers
                "apple_juice" -> R.drawable.apple_juice
                "fresh_fruits" -> R.drawable.fresh_fruits
                "croissant" -> R.drawable.croissant
                "bread_basket" -> R.drawable.bread_basket
                else -> R.drawable.placeholder
            }

            Image(
                painter = painterResource(id = imageResId),
                contentDescription = menuItem.name,
                modifier = Modifier.fillMaxWidth().height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = menuItem.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Цена: ${menuItem.price} ₽",
                fontSize = 16.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Блок БЖУ
            Text("БЖУ на 100 г:", fontSize = 16.sp)
            Text("Белки: ${menuItem.proteins} г", fontSize = 14.sp)
            Text("Жиры: ${menuItem.fats} г", fontSize = 14.sp)
            Text("Углеводы: ${menuItem.carbs} г", fontSize = 14.sp)
            Text("Калории: ${menuItem.calories} ккал", fontSize = 14.sp)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ингредиенты: ${menuItem.ingredients}",
                fontSize = 14.sp
            )
        }
    }
}
