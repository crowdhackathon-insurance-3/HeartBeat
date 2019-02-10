using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RecorderManager : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void RecordFile()
    {
        AudioSource audioSource = GetComponent<AudioSource>();
        //audioSource.clip = Microphone.Start("Built-in Microphone", true, 10, 44100);
        audioSource.clip = Microphone.Start(null, true, 10, 44100);
        audioSource.Play();
    }
}
