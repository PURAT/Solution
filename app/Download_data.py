from neuralNetwork import model2
from neuralNetwork import dataset
import numpy as np


from firebase import firebase

firebase =firebase.FirebaseApplication("https://booknet-e7432.firebaseio.com/", None)

book_data = np.array(list(set(dataset.book_id)))
user_id = firebase.get('/ned/-M31JVrY46rPwcOljBCX', 'name')
user = np.array([user_id for i in range(len(book_data))])


predictions = model2.predict(user, book_data)

predictions = np.array([a[0] for a in predictions])

recommended_book_ids = (-predictions).argsort()[:1]

recommended_book_ids = firebase.post('/Recomendation_id/', recommended_book_ids)

