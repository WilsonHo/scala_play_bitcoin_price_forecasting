import gc
import datetime
import pandas as pd
import numpy as np

import keras
from keras.models import Sequential
from keras.layers import Activation, Dense
from keras.layers import LSTM
from keras.layers import Dropout


def load_model(currency="bitcoin"):
    # load json and create model
    import os
    cwd = os.getcwd()
    print (cwd)
    json_file = open('./python/{}_model.json'.format(currency), 'r')
    loaded_model_json = json_file.read()
    json_file.close()
    regressor = model_from_json(loaded_model_json)
    regressor.load_weights("./python/{}_model.h5".format(currency))
    print("Loaded model from disk")
    return regressor
    # load weights into new model


def predict_price(last15DaysPrice, currency="bitcoin"):
    model = load_model(currency)
    min_max_scaler = MinMaxScaler()
    df_test = df[last15DaysPrice]
    test_set = df_test.values
    inputs = np.reshape(test_set, (len(test_set), 1))
    inputs = min_max_scaler.transform(inputs)
    inputs = np.reshape(inputs, (len(inputs), 1, 1))
    predicted_price = model.predict(inputs)
    predicted_price = min_max_scaler.inverse_transform(predicted_price)
    return predicted_price
