using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Firebase.Messaging;

public class FireBaseManager : MonoBehaviour
{
    public Text text;
    public void Start()
    {
        Debug.Log("ante");
        FirebaseMessaging.TokenReceived += OnTokenReceived;
        FirebaseMessaging.MessageReceived += OnMessageReceived;
        FirebaseMessaging.SubscribeAsync("testTopic");
    }

    public void OnTokenReceived(object sender, TokenReceivedEventArgs token)
    {
        Debug.Log("Received Registration Token: " + token.Token);
        text.text = token.Token;
    }

    public void OnMessageReceived(object sender, MessageReceivedEventArgs e)
    {
        Debug.Log("Received a new message from: " + e.Message.From);
        Debug.Log("Received a new message from: " + e.Message.Data);
        //Debug.Log("Received a new message from: " + e.Message.);

        text.text = e.Message.Data["test"];
    }
}
