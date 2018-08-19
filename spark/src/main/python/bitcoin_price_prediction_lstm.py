# python3 -m pip install --upgrade https://storage.googleapis.com/tensorflow/mac/cpu/tensorflow-0.12.0-py3-none-any.whl

import numpy as np
import pandas as pd
from matplotlib import pyplot as plt

from keras.models import Sequential
from keras.layers import Dense
from keras.layers import LSTM

from sklearn.preprocessing import MinMaxScaler

min_max_scaler = MinMaxScaler()

df = pd.read_csv("./../resources/market-price.csv", header=None)
# df_norm = df.drop(['Date'], 1, inplace=True)
df_norm = df.drop(df.columns[0], 1, inplace=True)

prediction_days = 30

df_train = df[:len(df) - prediction_days]
# print (df_train)

df_test = df[len(df) - prediction_days:]
# print (df_test)

training_set = df_train.values
# print (training_set)

training_set = min_max_scaler.fit_transform(training_set)
# print (training_set)

x_train = training_set[0:len(training_set) - 1]
# print (x_train)

y_train = training_set[1:len(training_set)]
# print (y_train)

x_train = np.reshape(x_train, (len(x_train), 1, 1))

num_units = 4
activation_function = 'sigmoid'
optimizer = 'adam'
loss_function = 'mean_squared_error'
batch_size = 5
num_epochs = 100

# Initialize the RNN
regressor = Sequential()
# Adding the input layer and the LSTM layer
lstm = LSTM(units=num_units, activation=activation_function, input_shape=(None, 1))
regressor.add(lstm)

# Adding the output layer
regressor.add(Dense(units = 1))

# Compiling the RNN
regressor.compile(optimizer = optimizer, loss = loss_function)

# Using the training set to train the model
regressor.fit(x_train, y_train, batch_size = batch_size, epochs = num_epochs)

test_set = df_test.values

inputs = np.reshape(test_set, (len(test_set), 1))
inputs = min_max_scaler.transform(inputs)
inputs = np.reshape(inputs, (len(inputs), 1, 1))

predicted_price = regressor.predict(inputs)
predicted_price = min_max_scaler.inverse_transform(predicted_price)

plt.figure(figsize=(25, 25), dpi=80, facecolor = 'w', edgecolor = 'k')

plt.plot(test_set[:, 0], color='red', label='Real BTC Price')
plt.plot(predicted_price[:, 0], color = 'blue', label = 'Predicted BTC Price')

plt.title('BTC Price Prediction', fontsize = 40)
plt.xlabel('Time', fontsize=40)
plt.ylabel('BTC Price(USD)', fontsize = 40)
plt.legend(loc = 'best')
plt.show()
