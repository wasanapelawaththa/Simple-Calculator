// 1. Package and imports
package com.firstapp.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 2. MainActivity with Compose content
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CalculatorScreen() // <-- Call your screen here
            }
        }
    }
}

// 3. Add your CalculatorScreen composable here
@Composable
fun CalculatorScreen() {
    var input by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = input,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        val buttons = listOf(
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "x"),
            listOf("1", "2", "3", "-"),
            listOf("C", "0", "=", "+")
        )

        for (row in buttons) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (btn in row) {
                    CalculatorButton(
                        symbol = btn,
                        onClick = {
                            when (btn) {
                                "C" -> input = ""
                                "=" -> input = evaluateExpression(input)
                                else -> input += btn
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}

// 4. Reusable button
@Composable
fun CalculatorButton(
    symbol: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(Color(0xFFE0E0E0))
            .clickable { onClick() }
    ) {
        Text(
            text = symbol,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

// 5. Expression evaluator
fun evaluateExpression(expression: String): String {
    return try {
        val result = ExpressionEvaluator().evaluate(expression)
        result.toString()
    } catch (e: Exception) {
        "Error"
    }
}

class ExpressionEvaluator {
    fun evaluate(expr: String): Double {
        val sanitized = expr.replace("x", "*")
        return evaluateSimple(sanitized)
    }

    private fun evaluateSimple(expression: String): Double {
        val tokens = tokenize(expression)
        val postfix = infixToPostfix(tokens)
        return evaluatePostfix(postfix)
    }

    private fun tokenize(expression: String): List<String> {
        val result = mutableListOf<String>()
        var number = ""
        for (char in expression) {
            if (char.isDigit() || char == '.') {
                number += char
            } else {
                if (number.isNotEmpty()) {
                    result.add(number)
                    number = ""
                }
                result.add(char.toString())
            }
        }
        if (number.isNotEmpty()) result.add(number)
        return result
    }

    private fun infixToPostfix(tokens: List<String>): List<String> {
        val output = mutableListOf<String>()
        val stack = mutableListOf<String>()
        val precedence = mapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2)

        for (token in tokens) {
            if (token.toDoubleOrNull() != null) {
                output.add(token)
            } else {
                while (stack.isNotEmpty() && precedence.getOrDefault(stack.last(), 0) >= precedence.getOrDefault(token, 0)) {
                    output.add(stack.removeLast())
                }
                stack.add(token)
            }
        }

        while (stack.isNotEmpty()) {
            output.add(stack.removeLast())
        }

        return output
    }

    private fun evaluatePostfix(tokens: List<String>): Double {
        val stack = mutableListOf<Double>()

        for (token in tokens) {
            val num = token.toDoubleOrNull()
            if (num != null) {
                stack.add(num)
            } else {
                val b = stack.removeLast()
                val a = stack.removeLast()
                val result = when (token) {
                    "+" -> a + b
                    "-" -> a - b
                    "*" -> a * b
                    "/" -> a / b
                    else -> throw IllegalArgumentException("Unknown operator: $token")
                }
                stack.add(result)
            }
        }

        return stack.first()
    }
}

