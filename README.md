# 🚀 Jetpack Compose - Drawer Pushing Content

Jetpack Compose has transformed Android UI development by providing a **declarative approach** to building interfaces. In this project, we implement a **custom drawer layout** that **pushes content** instead of overlaying it.

## 📌 Features
✅ Smooth **push animation** for the drawer  
✅ Custom **gesture handling** for swipes  
✅ Built with **Jetpack Compose Layout**  
✅ **Lightweight & highly customizable**  

## 🎬 Demo

### 📌 Phase 1  
<img src="https://github.com/user-attachments/assets/8909436f-b308-4056-bd6b-003858438351" width="400" alt="Drawer Animation Phase 1"/>

### 📌 Phase 2  
https://github.com/user-attachments/assets/e5d30d6d-4bec-4b34-9716-2764430d5ac6

---

## 🛠️ Tech Stack  
- **Jetpack Compose** for UI  
- **Kotlin** as the programming language  
- **State Management** with `remember` & `MutableState`  
- **Custom Layouts & Animations**  

---

## 📜 Code Snippet  
Here’s a small preview of how we handle the **custom drawer animation**:

```kotlin
@Composable
fun DrawerContainer(
    isDrawerOpened: Boolean,
    drawerWidth: Dp = 200.dp,
    onSwipe: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    val transition = updateTransition(targetState = isDrawerOpened, label = "")

    val collapseFraction = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 400) },
        label = ""
    ) { state -> if (state) 1f else 0f }

    Layout(
        content = content,
        modifier = Modifier.pointerInput(Unit) {
            detectHorizontalDragGestures { _, dragAmount ->
                if (!transition.isRunning) {
                    onSwipe(dragAmount > 0)
                }
            }
        }
    ) { measurables, constraints ->
        val drawerPlaceable = measurables[0].measure(Constraints.fixed(drawerWidth.roundToPx(), constraints.maxHeight))
        val contentPlaceable = measurables[1].measure(constraints)

        val drawerX = lerp(-drawerWidth.roundToPx().toFloat(), 0f, collapseFraction.value).roundToInt()
        val contentX = lerp(0f, drawerWidth.roundToPx().toFloat(), collapseFraction.value).roundToInt()

        layout(constraints.maxWidth, constraints.maxHeight) {
            drawerPlaceable.place(x = drawerX, y = 0)
            contentPlaceable.place(x = contentX, y = 0)
        }
    }
}
```
