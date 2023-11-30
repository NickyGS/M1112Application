package com.example.myapplication;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int hungerLevel;
    private int tirednessLevel;
    private int boredomLevel;
    private int happinessLevel;
    private int angerLevel;
    private TextView hungerTextView;
    private TextView tirednessTextView;
    private TextView boredomTextView;
    private TextView happinessTextView;
    private TextView angerTextView;
    private CountDownTimer hungerTimer;
    private CountDownTimer tirednessTimer;
    boolean angerIncreased = false;
    boolean angerIncreased2 = false;
    boolean angerIncreased3 = false;

    private void updateAngerLevel() {

        ImageView heartImage1 = findViewById(R.id.krass3);
        ImageView heartImage2 = findViewById(R.id.krass2);
        ImageView heartImage3 = findViewById(R.id.krass1);

        if (hungerLevel >= 10) {
            if (!angerIncreased) {
                angerLevel += 1;
                angerIncreased = true;
                // Изменяем изображение сердца на белое, если предыдущее сердце красное
                if (heartImage1.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.krass).getConstantState()) {
                    heartImage1.setImageResource(R.drawable.belo);
                } else {
                    heartImage2.setImageResource(R.drawable.belo);
                }
            }
        } else {
            if (hungerLevel == 9 && angerIncreased) {
                // Уменьшаем уровень злости
                if (angerLevel > 0) {
                    angerLevel -= 1;
                    // Возвращаем предыдущее сердце красным, если оно было белым
                    if (heartImage2.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.belo).getConstantState()) {
                        heartImage2.setImageResource(R.drawable.krass);
                    } else {
                        heartImage1.setImageResource(R.drawable.krass);
                    }
                }
                angerIncreased = false;
            }
        }

// Аналогичная логика для остальных уровней злости и соответствующих сердец
        if (tirednessLevel >= 10) {
            if (!angerIncreased2) {
                angerLevel += 1;
                angerIncreased2 = true;
                if (heartImage2.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.krass).getConstantState()) {
                    heartImage2.setImageResource(R.drawable.belo);
                } else {
                    heartImage3.setImageResource(R.drawable.belo);
                }
            }
        } else {
            if (tirednessLevel == 9 && angerIncreased2) {
                if (angerLevel > 0) {
                    angerLevel -= 1;
                    if (heartImage3.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.belo).getConstantState()) {
                        heartImage3.setImageResource(R.drawable.krass);
                    } else {
                        heartImage2.setImageResource(R.drawable.krass);
                    }
                }
                angerIncreased2 = false;
            }
        }

        if (happinessLevel >= 10) {
            if (!angerIncreased3) {
                angerLevel += 1;
                angerIncreased3 = true;
                if (heartImage3.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.krass).getConstantState()) {
                    heartImage3.setImageResource(R.drawable.belo);
                } else {
                    heartImage1.setImageResource(R.drawable.belo);
                }
            }
        } else {
            if (happinessLevel == 9 && angerIncreased3) {
                if (angerLevel > 0) {
                    angerLevel -= 1;
                    if (heartImage1.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.belo).getConstantState()) {
                        heartImage1.setImageResource(R.drawable.krass);
                    } else {
                        heartImage3.setImageResource(R.drawable.krass);
                    }
                }
                angerIncreased3 = false;
            }
        }

        if (angerLevel == 3) {
            // Останавливаем таймеры голода и усталости
            hungerTimer.cancel();
            tirednessTimer.cancel();
            // Изменяем изображение существа на изображение покойника
            ImageView creatureImage = findViewById(R.id.creatureImageView);
            creatureImage.setImageResource(R.drawable.dead_image);
// Останавливаем игровой процесс
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            // Выводим сообщение о конце игры
            Toast.makeText(MainActivity.this, "Игра окончена", Toast.LENGTH_SHORT).show();
        }
    }

    private int getHeartImageId(int heartIndex) {
        int heartImageId = getResources().getIdentifier("heartImage" + heartIndex, "id", getPackageName());
        return heartImageId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Получение ссылок на TextView
        hungerTextView = findViewById(R.id.hungerTextView);
        tirednessTextView = findViewById(R.id.tirednessTextView);
        boredomTextView = findViewById(R.id.boredomTextView);
        happinessTextView = findViewById(R.id.happinessTextView);
        angerTextView = findViewById(R.id.angerTextView);
        // Инициализация значений уровня голода, усталости, скучны, счастья и злости
        hungerLevel = 0;
        tirednessLevel = 0;
        boredomLevel = 0;
        happinessLevel = 0;
        angerLevel = 0;
        // Обновление значений TextView
        updateTextViews();

        // Инициализация таймера на уровень голода
        hungerTimer = new CountDownTimer(Long.MAX_VALUE, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Увеличение уровня голода
                hungerLevel++;
                // Обновление значений TextView
                updateTextViews();
                // Обновляем значения злости
                updateAngerLevel();
            }

            @Override
            public void onFinish() {
                // Реагировать на смерть существа
                handleDeath();
            }
        };

        // Инициализация таймера на уровень усталости
        tirednessTimer = new CountDownTimer(Long.MAX_VALUE, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Увеличение уровня усталости
                tirednessLevel++;
                // Обновление значений TextView
                updateTextViews();
                // Обновляем значения злости
                updateAngerLevel();
            }

            @Override
            public void onFinish() {
                // Реагировать на фатальное состояние существа
                handleDeath();
            }
        };
// Запуск таймеров
        hungerTimer.start();
        tirednessTimer.start();
    }

    // Обработчик нажатия кнопки "Накормить"
    public void onFeedButtonClick(View view) {
        // Уменьшение уровня голода
        if (hungerLevel > 0) {
            hungerLevel--;
        }
        // Обновление значений TextView
        updateTextViews();
        // Обновляем значения злости
        updateAngerLevel();
    }

    // Обработчик нажатия кнопки "Спать"
    public void onSleepButtonClick(View view) {
        // Уменьшение уровня усталости
        if (tirednessLevel > 0) {
            tirednessLevel--;
        }
        // Увеличение уровня счастья
        happinessLevel++;
        // Обновление значений TextView
        updateTextViews();
        // Обновляем значения злости
        updateAngerLevel();
    }

    // Обработчик нажатия кнопки "Играть"
    public void onPlayButtonClick(View view) {
        // Увеличение уровня скучны
        boredomLevel++;
        // Уменьшение уровня счастья
        if (happinessLevel > 0) {
            happinessLevel--;
        }
        // Обновление значений TextView
        updateTextViews();
        // Обновляем значения злости
        updateAngerLevel();
    }

    // Обработчик нажатия кнопки "Остановить игру"
    public void onStopGameButtonClick(View view) {
        // Останавливаем таймеры голода и усталости
        hungerTimer.cancel();
        tirednessTimer.cancel();
        // Останавливаем игровой процесс
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        // Выводим сообщение о конце игры
        Toast.makeText(MainActivity.this, "Игра окончена", Toast.LENGTH_SHORT).show();
    }

    private void updateTextViews () {
        hungerTextView.setText("Голод: " + hungerLevel);
        tirednessTextView.setText("Усталость: " + tirednessLevel);
        boredomTextView.setText("Счастье: " + boredomLevel);
        happinessTextView.setText("Грусть: " + happinessLevel);
        angerTextView.setText("Злость: " + angerLevel);
    }

    // Метод для реагирования на смерть существа
    private void handleDeath() {
        // Останавливаем игровой процесс
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        // Выводим сообщение о конце игры
        Toast.makeText(MainActivity.this, "Игра окончена", Toast.LENGTH_SHORT).show();
    }
}