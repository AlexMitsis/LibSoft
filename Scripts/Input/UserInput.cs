// GENERATED AUTOMATICALLY FROM 'Assets/Scripts/Input/UserInput.inputactions'

using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine.InputSystem;
using UnityEngine.InputSystem.Utilities;

public class @UserInput : IInputActionCollection, IDisposable
{
    public InputActionAsset asset { get; }
    public @UserInput()
    {
        asset = InputActionAsset.FromJson(@"{
    ""name"": ""UserInput"",
    ""maps"": [
        {
            ""name"": ""UI"",
            ""id"": ""416d645a-762a-47ee-8b9e-5479d54c809e"",
            ""actions"": [
                {
                    ""name"": ""ChangeTab"",
                    ""type"": ""Button"",
                    ""id"": ""ebe00e5b-a83c-4dfd-8c70-2c5f6258ffb5"",
                    ""expectedControlType"": ""Button"",
                    ""processors"": """",
                    ""interactions"": """"
                }
            ],
            ""bindings"": [
                {
                    ""name"": """",
                    ""id"": ""cb5852ed-bbae-4d97-b9fd-8e390e712692"",
                    ""path"": ""<Keyboard>/tab"",
                    ""interactions"": ""Press"",
                    ""processors"": """",
                    ""groups"": """",
                    ""action"": ""ChangeTab"",
                    ""isComposite"": false,
                    ""isPartOfComposite"": false
                }
            ]
        }
    ],
    ""controlSchemes"": []
}");
        // UI
        m_UI = asset.FindActionMap("UI", throwIfNotFound: true);
        m_UI_ChangeTab = m_UI.FindAction("ChangeTab", throwIfNotFound: true);
    }

    public void Dispose()
    {
        UnityEngine.Object.Destroy(asset);
    }

    public InputBinding? bindingMask
    {
        get => asset.bindingMask;
        set => asset.bindingMask = value;
    }

    public ReadOnlyArray<InputDevice>? devices
    {
        get => asset.devices;
        set => asset.devices = value;
    }

    public ReadOnlyArray<InputControlScheme> controlSchemes => asset.controlSchemes;

    public bool Contains(InputAction action)
    {
        return asset.Contains(action);
    }

    public IEnumerator<InputAction> GetEnumerator()
    {
        return asset.GetEnumerator();
    }

    IEnumerator IEnumerable.GetEnumerator()
    {
        return GetEnumerator();
    }

    public void Enable()
    {
        asset.Enable();
    }

    public void Disable()
    {
        asset.Disable();
    }

    // UI
    private readonly InputActionMap m_UI;
    private IUIActions m_UIActionsCallbackInterface;
    private readonly InputAction m_UI_ChangeTab;
    public struct UIActions
    {
        private @UserInput m_Wrapper;
        public UIActions(@UserInput wrapper) { m_Wrapper = wrapper; }
        public InputAction @ChangeTab => m_Wrapper.m_UI_ChangeTab;
        public InputActionMap Get() { return m_Wrapper.m_UI; }
        public void Enable() { Get().Enable(); }
        public void Disable() { Get().Disable(); }
        public bool enabled => Get().enabled;
        public static implicit operator InputActionMap(UIActions set) { return set.Get(); }
        public void SetCallbacks(IUIActions instance)
        {
            if (m_Wrapper.m_UIActionsCallbackInterface != null)
            {
                @ChangeTab.started -= m_Wrapper.m_UIActionsCallbackInterface.OnChangeTab;
                @ChangeTab.performed -= m_Wrapper.m_UIActionsCallbackInterface.OnChangeTab;
                @ChangeTab.canceled -= m_Wrapper.m_UIActionsCallbackInterface.OnChangeTab;
            }
            m_Wrapper.m_UIActionsCallbackInterface = instance;
            if (instance != null)
            {
                @ChangeTab.started += instance.OnChangeTab;
                @ChangeTab.performed += instance.OnChangeTab;
                @ChangeTab.canceled += instance.OnChangeTab;
            }
        }
    }
    public UIActions @UI => new UIActions(this);
    public interface IUIActions
    {
        void OnChangeTab(InputAction.CallbackContext context);
    }
}
