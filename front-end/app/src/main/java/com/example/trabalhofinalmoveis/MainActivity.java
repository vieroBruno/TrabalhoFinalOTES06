package com.example.trabalhofinalmoveis;




import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trabalhofinalmoveis.R;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText cityInput;
    private Button btnSearch;
    private TextView resultView;
    private TextView weatherDescription;
    private TextView temperature;
    private TextView minTemp;
    private TextView maxTemp;
    private TextView feelsLike;
    private TextView humidityText;
    private TextView visibilityText;
    private TextView sunriseText;
    private TextView sunsetText;
    private TextView pressureText;
    private TextView windText;

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityInput = findViewById(R.id.cityInput);
        btnSearch = findViewById(R.id.btnSearch);
        resultView = findViewById(R.id.resultView);
        weatherDescription = findViewById(R.id.weatherDescription);
        temperature = findViewById(R.id.temperature);
        maxTemp = findViewById(R.id.maxTemperatureText);
        minTemp = findViewById(R.id.minTemperatureText);
        feelsLike = findViewById(R.id.feelsLike);
        humidityText = findViewById(R.id.humidityText);
        visibilityText = findViewById(R.id.visibilityText);
        sunriseText = findViewById(R.id.sunriseText);
        sunsetText = findViewById(R.id.sunsetText);
        pressureText = findViewById(R.id.pressureText);
        windText = findViewById(R.id.windText);

        btnSearch.setOnClickListener(view -> {
            String city = cityInput.getText().toString().trim();
            if (!city.isEmpty()) {
                fetchWeather(city);
            }
        });
    }
    private String getWindDirection(double degree) {

        if (degree >= 337.5 || degree < 22.5) return "N";
        if (degree >= 22.5 && degree < 67.5) return "NE";
        if (degree >= 67.5 && degree < 112.5) return "E";
        if (degree >= 112.5 && degree < 157.5) return "SE";
        if (degree >= 157.5 && degree < 202.5) return "S";
        if (degree >= 202.5 && degree < 247.5) return "SW";
        if (degree >= 247.5 && degree < 292.5) return "W";
        if (degree >= 292.5 && degree < 337.5) return "NW";

        return "";
    }

    private void setDefaultText(){
        runOnUiThread(() -> maxTemp.setText("--º"));
        runOnUiThread(() -> minTemp.setText("--º"));
        runOnUiThread(() -> feelsLike.setText("--º"));
        runOnUiThread(() -> visibilityText.setText("-- km"));
        runOnUiThread(() -> humidityText.setText("--%"));
        runOnUiThread(() -> sunriseText.setText("--"));
        runOnUiThread(() -> sunsetText.setText("--"));
        runOnUiThread(() -> pressureText.setText("-- Bar"));
        runOnUiThread(() -> windText.setText("--"));
    }
    private void fetchWeather(String city) {
        String url = "http://10.0.2.2:8081/api/weather/" + city;

        Request request = new Request.Builder()
                .url(url)
                .build();

         client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> {
                    resultView.setText("Erro");
                    temperature.setText("500");
                    weatherDescription.setText("Verifique o servidor");
                    setDefaultText();
                    Toast.makeText(MainActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {

                    runOnUiThread(() -> {
                        resultView.setText("Erro");
                        temperature.setText("404");
                        weatherDescription.setText("Cidade não encontrada");
                        setDefaultText();
                        Toast.makeText(MainActivity.this, "Ocorreu um erro!", Toast.LENGTH_SHORT).show();
                    });
                    return;
                }

                String json = response.body().string();
                try {
                    JSONObject obj = new JSONObject(json);

                    double temp = obj.getJSONObject("main").getDouble("temp");
                    String tempResult = String.valueOf((Math.round(temp)))+"º";

                    String desc = obj.getJSONArray("weather")
                            .getJSONObject(0)
                            .getString("description");

                    String cityName = obj.getString("name");

                    double minTemperature = obj.getJSONObject("main").getDouble("temp_min");
                    String minTempResult ="Mín.: "+ (int) Math.floor(minTemperature)+"º";

                    double maxTemperature = obj.getJSONObject("main").getDouble("temp_max");
                    String maxTempResult = "Máx.: "+(int) Math.ceil(maxTemperature)+"º";

                    double feelsLikeTemp = obj.getJSONObject("main").getDouble("feels_like");
                    String feelsLikeResult = String.valueOf((Math.round(feelsLikeTemp)))+"º";

                    int visibility = obj.getInt("visibility")/1000;
                    String visibilityResult = visibility+" Km";

                    int humidity = obj.getJSONObject("main").getInt("humidity");
                    String humidityResult = humidity+"%";

                    long sunrise = obj.getJSONObject("sys").getLong("sunrise");
                    long sunset = obj.getJSONObject("sys").getLong("sunset");
                    long timezoneOffset = obj.getLong("timezone");
                    Date sunriseDate = new Date((sunrise + timezoneOffset) * 1000L);
                    Date sunsetDate = new Date((sunset + timezoneOffset) * 1000L);
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                    String sunriseFormatted = sdf.format(sunriseDate);
                    String sunsetFormatted = sdf.format(sunsetDate);

                    double pressureHpa = obj.getJSONObject("main").getDouble("pressure");
                    double pressureBar = pressureHpa / 1000.0;
                    String pressureFormatted = String.format(Locale.getDefault(), "%.3f Bar", pressureBar);

                    double windSpeed = obj.getJSONObject("wind").getDouble("speed");
                    double windDeg = obj.getJSONObject("wind").getDouble("deg");
                    String direction = getWindDirection(windDeg);
                    double windKmh = windSpeed * 3.6;
                    String windFormatted = String.format(Locale.getDefault(),
                            "%.1f %s", windKmh, direction);

                    runOnUiThread(() -> {
                        maxTemp.setText(maxTempResult);
                        minTemp.setText(minTempResult);
                        temperature.setText(tempResult);
                        weatherDescription.setText(desc);
                        resultView.setText(cityName);
                        feelsLike.setText(feelsLikeResult);
                        visibilityText.setText(visibilityResult);
                        humidityText.setText(humidityResult);
                        sunriseText.setText(sunriseFormatted);
                        sunsetText.setText(sunsetFormatted);
                        pressureText.setText(pressureFormatted);
                        windText.setText(windFormatted);
                    });



                } catch (Exception e) {
                    runOnUiThread(() -> resultView.setText("Erro ao processar dados."));
                }
            }
        });
    }
}
