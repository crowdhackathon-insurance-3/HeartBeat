using System.Collections;
using System.Collections.Generic;
using System.IO;
using System;
using UnityEngine;

public class RecorderManager : MonoBehaviour
{
    private AudioSource audioSource;
    private static int HEADER_SIZE=44;
    // Start is called before the first frame update
    void Start()
    {
        audioSource = GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    public void RecordFile()
    {
        //audioSource.clip = Microphone.Start("Built-in Microphone", true, 10, 44100);
        Debug.Log(Microphone.devices);
        audioSource.clip = Microphone.Start(null, true, 10, 44100);
        StartCoroutine(StopRecording(5));
    }

    public IEnumerator StopRecording(float _secs)
    {
        yield return new WaitForSeconds(_secs);
        Microphone.End(null);
        audioSource.Play();
        SavWav.Save("myfile", audioSource.clip);
    }

  
}
