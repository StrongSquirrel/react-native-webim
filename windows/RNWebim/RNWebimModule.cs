using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Webim.RNWebim
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNWebimModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNWebimModule"/>.
        /// </summary>
        internal RNWebimModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNWebim";
            }
        }
    }
}
